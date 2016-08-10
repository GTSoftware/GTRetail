Attribute VB_Name = "general"
Option Explicit

Private DBConn As New clsDBConnection

Public comPort As Integer
Public modeloFiscal As Integer
Public idUsuarioLogueado As Long

Public Function executeQuery(ByVal query As String) As ADODB.recordset
    
    Set executeQuery = DBConn.executeQuery(query)
    
End Function

Public Function LogIn(userName As String, password As String)
    LogIn = False
    
    Dim stmt As ADODB.Command
    Set stmt = New ADODB.Command
    stmt.CommandText = "SELECT Id FROM Seguridad_Usuarios WHERE UserName = ? AND PassWord = ? AND Activo = 1"
    stmt.CommandType = adCmdText
    'stmt.Prepared = True
    
    Dim param As ADODB.Parameter
    Set param = New ADODB.Parameter
    With param
        .Name = "username"
        .Type = adVarChar
        .Value = "user"
        .Size = 30
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "password"
        .Type = adVarChar
        .Value = "password"
        .Size = 30
    End With
    stmt.Parameters.Append param
    
    Set stmt.ActiveConnection = DBConn.getConnection
    stmt("username") = userName
    stmt("password") = password
    Dim result As ADODB.recordset
    
    Debug.Print stmt.CommandText
    Set result = stmt.Execute
    
    If Not result.EOF Then
        LogIn = True
        userName = userName
        idUsuarioLogueado = result.Fields("id")
    End If
End Function
'Obtiene los comprobantes entre las fechas dadas
Public Function obtenerComprobantes(fechaDesde As Date, fechaHasta As Date, impresas As Boolean, tamPagina As Integer) As ADODB.recordset
    Dim stmt As ADODB.Command
    Set stmt = New ADODB.Command
    Dim sql As String
    sql = "SELECT TOP " & tamPagina & " GC.ID,GC.Letra+' '+Gc.Punto_Vta+'-'+Right('00000000' + cast(Gc.Nro_Cmp as Varchar),8) as Comprobante,"
    sql = sql + "IsNull((Select SUM(GCD.ImporteUnitario*GCD.Cantidad + GCD.ImporteIva) FROM Gestion_Comprobantes_Detalle GCD WHERE GCD.Id_Comprobante = GC.Id),0) AS [Importe Total],"
    sql = sql + "IsNull((Select SUM(GCD.ImporteIva) FROM Gestion_Comprobantes_Detalle GCD WHERE GCD.Id_Comprobante = GC.Id),0) AS [IVA],"
    sql = sql + "Cli.Nombre as Cliente, CUIT,TI.Nombre as Cond_IVA ,Gc.Fecha,Gc.Impreso "
    sql = sql + "FROM Gestion_Comprobantes GC JOIN Clientes Cli ON GC.Id_entidad = Cli.Id JOIN TiposIva TI ON TI.Id = Cli.condicion WHERE (Fecha BETWEEN ? AND ?) AND Id_TipoComprobante = 1 AND Impreso = ? AND Estado = 0 ORDER BY GC.Id"
    stmt.CommandText = sql
    stmt.CommandType = adCmdText
    'stmt.Prepared = True
    
    Dim param As ADODB.Parameter
    Set param = New ADODB.Parameter
    With param
        .Name = "fechaDesde"
        .Type = adDBTimeStamp
        
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "fechaHasta"
        .Type = adDBTimeStamp
        
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "impreso"
        .Type = adBoolean
        
    End With
    stmt.Parameters.Append param
    
    Set stmt.ActiveConnection = DBConn.getConnection
    stmt("fechaDesde") = fechaDesde
    stmt("fechaHasta") = fechaHasta
    stmt("impreso") = impresas
    Dim result As ADODB.recordset
    Debug.Print stmt.CommandText
    
    Set obtenerComprobantes = stmt.Execute
End Function

Public Function obtenerLineasComprobante(id As Long) As ADODB.recordset
    Dim stmt As ADODB.Command
    Set stmt = New ADODB.Command
    Dim sql As String
    sql = "SELECT Descripcion,cantidad,ImporteUnitario + ImporteIva As ImporteUnit,PorcIVA "
    sql = sql + "FROM Gestion_Comprobantes_Detalle WHERE Id_Comprobante = ?"
    stmt.CommandText = sql
    stmt.CommandType = adCmdText
    'stmt.Prepared = True
    
    Dim param As ADODB.Parameter
    Set param = New ADODB.Parameter
    With param
        .Name = "id"
        .Type = adBigInt
        .Value = 0
    End With
    stmt.Parameters.Append param
    
    Set stmt.ActiveConnection = DBConn.getConnection
    stmt("id") = id
    
    Dim result As ADODB.recordset
    Debug.Print stmt.CommandText
    
    Set obtenerLineasComprobante = stmt.Execute
End Function

'Registra los datos de facturacion en la tabla de comprobantes
Public Function registrarFactura(idComprobante As Long, puntoVenta As String, numero As Long, fechaFacturacion As Date)
    Dim stmt As ADODB.Command
    Set stmt = New ADODB.Command
    stmt.CommandText = "UPDATE Gestion_Comprobantes SET Punto_Vta = ?, Nro_Cmp = ?,Impreso = 1,Fecha_Mod = GetDate(),Id_usuario_mod = ?, Fecha=? WHERE Id = ?"
    stmt.CommandType = adCmdText
    'stmt.Prepared = True
    
    Dim param As ADODB.Parameter
    Set param = New ADODB.Parameter
    With param
        .Name = "puntoVenta"
        .Type = adVarChar
        .Value = "0001"
        .Size = 4
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "numero"
        .Type = adInteger
        .Value = 0
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "idUsuario"
        .Type = adBigInt
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "fechaFactura"
        .Type = adDBTimeStamp
    End With
    stmt.Parameters.Append param
    Set param = New ADODB.Parameter
    With param
        .Name = "id"
        .Type = adBigInt
    End With
    stmt.Parameters.Append param
    
    Set stmt.ActiveConnection = DBConn.getConnection
    stmt("puntoVenta") = puntoVenta
    stmt("numero") = numero
    stmt("idUsuario") = idUsuarioLogueado
    stmt("fechaFactura") = fechaFacturacion
    stmt("id") = idComprobante
    
    Dim result As ADODB.recordset
    
    Debug.Print stmt.CommandText
    stmt.Execute
End Function

Public Function obtenerComprobante(id As Long) As ADODB.recordset
    Dim stmt As ADODB.Command
    Set stmt = New ADODB.Command
    Dim sql As String
    sql = "SELECT GC.ID,GC.Letra,GC.Letra+' '+Gc.Punto_Vta+'-'+Right('00000000' + cast(Gc.Nro_Cmp as Varchar),8) as Comprobante,"
    sql = sql + "IsNull((Select SUM(GCD.ImporteUnitario*GCD.Cantidad + GCD.ImporteIva) FROM Gestion_Comprobantes_Detalle GCD WHERE GCD.Id_Comprobante = GC.Id),0) AS [Importe Total],"
    sql = sql + "IsNull((Select SUM(GCD.ImporteIva) FROM Gestion_Comprobantes_Detalle GCD WHERE GCD.Id_Comprobante = GC.Id),0) AS [IVA],"
    sql = sql + "Cli.Nombre as Cliente,REPLACE(CUIT,'-','') as CUIT ,Cli.NroDoc AS DNI,Cli.Condicion as Id_Cond_Iva,TI.Nombre as Cond_IVA ,Gc.Fecha,Gc.Impreso "
    sql = sql + "FROM Gestion_Comprobantes GC JOIN Clientes Cli ON GC.Id_entidad = Cli.Id JOIN TiposIva TI ON TI.Id = Cli.condicion WHERE GC.Id = ?"
    stmt.CommandText = sql
    stmt.CommandType = adCmdText
    'stmt.Prepared = True
    
    Dim param As ADODB.Parameter
    Set param = New ADODB.Parameter
    With param
        .Name = "id"
        .Type = adBigInt
        .Value = 0
    End With
    stmt.Parameters.Append param
    
    Set stmt.ActiveConnection = DBConn.getConnection
    stmt("id") = id
    
    Dim result As ADODB.recordset
    Debug.Print stmt.CommandText
    
    Set obtenerComprobante = stmt.Execute
End Function
