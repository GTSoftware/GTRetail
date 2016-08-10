VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
  Persistable = 0  'NotPersistable
  DataBindingBehavior = 0  'vbNone
  DataSourceBehavior  = 0  'vbNone
  MTSTransactionMode  = 0  'NotAnMTSObject
END
Attribute VB_Name = "clsDBConnection"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = False
Attribute VB_Exposed = False
Attribute VB_Ext_KEY = "SavedWithClassBuilder6" ,"Yes"
Attribute VB_Ext_KEY = "Top_Level" ,"Yes"
'<CSCC>
'--------------------------------------------------------------------------------
'    Component  : clsDBCOnnection
'    Project    : FacturacionPlenario
'
'    Description: Clase para gestionar la conección con la base de datos
'
'    Modified   :
'--------------------------------------------------------------------------------
'</CSCC>

Option Explicit

Private adoConn       As ADODB.Connection

Private Servers       As Collection

Private serverNro     As Integer

' Variable to hold 'UserName' property value
Private m_strUserName As String

' Variable to hold 'Password' property value
Private m_strPassword As String

Private Enum clsDBConnectionErrorConstants

    InvalidData = 1
    
End Enum

' Variable to hold 'Provider' property value
Private m_strProvider As String

' Variable to hold 'Driver' property value
Private m_strDriver   As String

' Variable to hold 'Server' property value
Private m_strServer   As String

' Variable to hold 'DataBase' property value
Private m_strDataBase As String

Public Property Get DataBase() As String
    DataBase = m_strDataBase
End Property

Public Property Let DataBase(ByVal StrValue As String)

    If Not (StrValue = "") Then
        m_strDataBase = StrValue
    Else
        Err.Raise clsDBConnectionErrorConstants.InvalidData, "clsDBConnection", "La propiedad 'DataBase' no puede ser nula."
    End If

End Property

Public Property Get Server() As String
    Server = m_strServer
End Property

Public Property Let Server(ByVal StrValue As String)
    
    If Not (StrValue = "") Then
        m_strServer = StrValue
    Else
        Err.Raise clsDBConnectionErrorConstants.InvalidData, "clsDBConnection", "La propiedad 'Server' no puede ser nula."
    End If
    
End Property

Public Property Get Driver() As String
    Driver = m_strDriver
End Property

Public Property Let Driver(ByVal StrValue As String)

    If Not (StrValue = "") Then
        m_strDriver = StrValue
    Else
        Err.Raise clsDBConnectionErrorConstants.InvalidData, "clsDBConnection", "La propiedad 'Driver' no puede ser nula."
    End If
    
End Property

Public Property Get password() As String
    password = m_strPassword
End Property

Public Property Let password(ByVal StrValue As String)

    If Not (StrValue = "") Then
        m_strPassword = StrValue
    Else
        Err.Raise clsDBConnectionErrorConstants.InvalidData, "clsDBConnection", "La propiedad 'Password' no puede ser nula."
    End If
    
End Property

Public Property Get userName() As String
    userName = m_strUserName
End Property

Public Property Let userName(ByVal StrValue As String)
    
    If Not (StrValue = "") Then
        m_strUserName = StrValue
    Else
        Err.Raise clsDBConnectionErrorConstants.InvalidData, "clsDBConnection", "La propiedad 'UserName' no puede ser nula."
    End If
    
End Property

Private Sub Class_Initialize()
    Set Servers = New Collection
    Servers.Add "10.1.0.37"
    
    serverNro = 1
    Set adoConn = New ADODB.Connection
    Driver = "{SQL Server}"
    Server = Servers.Item(serverNro)
    userName = "plenario"
    password = "plenario256"
    DataBase = "PlenarioNET_Habitar_B"
    VerifyConnection
    'Exit Sub
    'If Not MySQLCrearDSN("Plenario", Server, DataBase, UserName, Password, 1433) Then
    '    MsgBox "Error al crear el DSN, puede que los reportes no funcionen correctamente", vbCritical, "DSN"
    'End If
    
End Sub

'Ejecuta la sentencia SQL y devuelve el recordset resultante
'CSEH: ErrLog
Public Function executeQuery(query As String) As ADODB.recordset

    '<EhHeader>
    On Error GoTo executeQuery_Err

    '</EhHeader>

1   VerifyConnection
    Debug.Print query
    Set executeQuery = adoConn.Execute(query)
    
    '<EhFooter>
    Exit Function

executeQuery_Err:
    
    Debug.Print Err.Number & ":" & Err.Description
    
    'MsgBox "El sistema se cerrará por funcionamiento incorrecto." & vbCrLf & "Se perderán los cambios que no se hayan guardado" & vbCrLf & "Contacte a su administrador de sistemas y envíe el reporte del fallos ubicado en:" & vbCrLf & App.Path & "\Errors.log", vbCritical, "Error"
    
    MsgBox "Se ha producido el siguiente error:" & vbCrLf & Err.Description & vbCrLf & vbCrLf & "Contacte a su administrador de sistemas.", vbCritical, "Error"
    
    Open App.Path & "\Errors.log" For Output As #1
    'Print #1, "Fecha" & vbTab & "Comando" & vbTab & "NroError" & vbTab & "ErrDesc"
    Print #1, Now & vbTab & """" & query & """" & vbTab & Err.Number & vbTab & """" & Err.Description & """"
    
    Close #1
    'End

    '</EhFooter>

End Function

'CSEH: ErrLog
Private Sub VerifyConnection()

    '<EhHeader>
    On Error GoTo VerifyConnection_Err

    '</EhHeader>
    
1   If adoConn.state = 0 Then
            
        Set adoConn = New ADODB.Connection

        Dim cadena_conexion As String

        cadena_conexion = "PROVIDER = MSDASQL;DRIVER=" & Driver & "; SERVER=" & Servers.Item(serverNro) & "; DATABASE=" & DataBase & " ;PWD=" & password & "; UID=" & userName
        adoConn.CursorLocation = adUseClient
        adoConn.Open cadena_conexion
            
    End If

    '<EhFooter>
    Exit Sub

VerifyConnection_Err:
    
    Debug.Print Err.Description

    If serverNro = 1 Then
        serverNro = 2
    Else
        serverNro = 1
    End If

    If MsgBox("Se ha producido el siguiente error al intentar conectar con el servidor:" & vbCrLf & Err.Description & vbCrLf & "Desea reintentar la conexión con el servidor:" & Servers.Item(serverNro) & "?", vbYesNo + vbDefaultButton1 + vbQuestion, "Conexion") = vbYes Then

        Resume 1

    Else
        MsgBox "El sistema se cerrará por funcionamiento incorrecto.", vbCritical, "Error"

        End

    End If

    '</EhFooter>

End Sub

Private Sub Class_Terminate()

    If adoConn.state = adStateClosed Then
        adoConn.Close
    End If
  'BorrarDSN "Global", C_MYSQL_ODBC
End Sub

Public Function getConnection() As ADODB.Connection
    Set getConnection = adoConn

End Function
