VERSION 5.00
Begin VB.Form frmLogin 
   BorderStyle     =   3  'Fixed Dialog
   Caption         =   "Login"
   ClientHeight    =   2445
   ClientLeft      =   2835
   ClientTop       =   3480
   ClientWidth     =   3750
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   1444.587
   ScaleMode       =   0  'User
   ScaleWidth      =   3521.047
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'CenterScreen
   Begin VB.ComboBox modeloFiscalCombo 
      Height          =   315
      ItemData        =   "frmLogin.frx":0000
      Left            =   1320
      List            =   "frmLogin.frx":0002
      Style           =   2  'Dropdown List
      TabIndex        =   8
      Top             =   1440
      Width           =   2295
   End
   Begin VB.ComboBox comPortCombo 
      Height          =   315
      ItemData        =   "frmLogin.frx":0004
      Left            =   1320
      List            =   "frmLogin.frx":0023
      Style           =   2  'Dropdown List
      TabIndex        =   6
      Top             =   960
      Width           =   2295
   End
   Begin VB.TextBox txtUserName 
      Height          =   345
      Left            =   1290
      TabIndex        =   1
      Top             =   135
      Width           =   2325
   End
   Begin VB.CommandButton cmdOK 
      Caption         =   "OK"
      Default         =   -1  'True
      Height          =   390
      Left            =   495
      TabIndex        =   4
      Top             =   1920
      Width           =   1140
   End
   Begin VB.CommandButton cmdCancel 
      Cancel          =   -1  'True
      Caption         =   "Cancel"
      Height          =   390
      Left            =   2100
      TabIndex        =   5
      Top             =   1920
      Width           =   1140
   End
   Begin VB.TextBox txtPassword 
      Height          =   345
      IMEMode         =   3  'DISABLE
      Left            =   1290
      PasswordChar    =   "*"
      TabIndex        =   3
      Top             =   525
      Width           =   2325
   End
   Begin VB.Label lblLabels 
      Caption         =   "Modelo Fiscal:"
      Height          =   270
      Index           =   3
      Left            =   120
      TabIndex        =   9
      Top             =   1440
      Width           =   1080
   End
   Begin VB.Label lblLabels 
      Caption         =   "Puerto Fiscal:"
      Height          =   270
      Index           =   2
      Left            =   120
      TabIndex        =   7
      Top             =   960
      Width           =   1080
   End
   Begin VB.Label lblLabels 
      Caption         =   "&User Name:"
      Height          =   270
      Index           =   0
      Left            =   105
      TabIndex        =   0
      Top             =   150
      Width           =   1080
   End
   Begin VB.Label lblLabels 
      Caption         =   "&Password:"
      Height          =   270
      Index           =   1
      Left            =   105
      TabIndex        =   2
      Top             =   540
      Width           =   1080
   End
End
Attribute VB_Name = "frmLogin"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Public LoginSucceeded As Boolean

Private Sub cmdCancel_Click()
    'set the global var to false
    'to denote a failed login
    LoginSucceeded = False
    Me.Hide
    End
End Sub

Private Sub cmdOK_Click()
    
    'check for correct password
    If LogIn(txtUserName.text, txtPassword.text) Then
        'place code to here to pass the
        'success to the calling sub
        'setting a global var is the easiest
        LoginSucceeded = True
        Me.Hide
        Load frmComprobantes
        comPort = comPortCombo.ListIndex + 1
        modeloFiscal = modeloFiscalCombo.ListIndex
        frmComprobantes.Show
        Unload Me
        'Call the main form
    Else
        MsgBox "Invalid Password, try again!", , "Login"
        txtPassword.SetFocus
        SendKeys "{Home}+{End}"
    End If
End Sub

Private Sub Form_Load()
    comPortCombo.ListIndex = 0
    loadModelosFiscales
End Sub

Private Sub loadModelosFiscales()
    modeloFiscalCombo.AddItem "Seleccione", 0
    modeloFiscalCombo.AddItem "MODELO_614", MODELO_614
    modeloFiscalCombo.AddItem "MODELO_615", MODELO_615
    modeloFiscalCombo.AddItem "MODELO_PR4", MODELO_PR4
    modeloFiscalCombo.AddItem "MODELO_950", MODELO_950
    modeloFiscalCombo.AddItem "MODELO_951", MODELO_951
    modeloFiscalCombo.AddItem "MODELO_262", MODELO_262
    modeloFiscalCombo.AddItem "MODELO_PJ20", MODELO_PJ20
    modeloFiscalCombo.AddItem "MODELO_P320", MODELO_P320

    modeloFiscalCombo.ListIndex = MODELO_615
End Sub
