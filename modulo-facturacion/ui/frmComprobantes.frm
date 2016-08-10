VERSION 5.00
Object = "{D9AF33E0-7C55-11D5-9151-0000E856BC17}#1.0#0"; "fiscal010724.ocx"
Object = "{831FDD16-0C5C-11D2-A9FC-0000F8754DA1}#2.1#0"; "mscomctl32.ocx"
Object = "{86CF1D34-0C5F-11D2-A9FC-0000F8754DA1}#2.0#0"; "mscomct2.ocx"
Begin VB.Form frmComprobantes 
   BorderStyle     =   3  'Fixed Dialog
   Caption         =   "Comprobantes"
   ClientHeight    =   5505
   ClientLeft      =   45
   ClientTop       =   330
   ClientWidth     =   11145
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   5505
   ScaleWidth      =   11145
   ShowInTaskbar   =   0   'False
   StartUpPosition =   3  'Windows Default
   Begin MSComctlLib.ProgressBar progress 
      Height          =   255
      Left            =   9120
      TabIndex        =   22
      Top             =   5280
      Width           =   1935
      _ExtentX        =   3413
      _ExtentY        =   450
      _Version        =   393216
      Appearance      =   1
   End
   Begin VB.TextBox tamPaginaField 
      Height          =   285
      Left            =   4800
      TabIndex        =   21
      Text            =   "150"
      Top             =   720
      Width           =   735
   End
   Begin VB.CommandButton cierreZButton 
      Caption         =   "Cierre Z"
      Height          =   495
      Left            =   8040
      TabIndex        =   19
      Top             =   4680
      Width           =   1455
   End
   Begin MSComctlLib.StatusBar statusBar 
      Align           =   2  'Align Bottom
      Height          =   255
      Left            =   0
      TabIndex        =   18
      Top             =   5250
      Width           =   11145
      _ExtentX        =   19659
      _ExtentY        =   450
      SimpleText      =   "Estado:"
      _Version        =   393216
      BeginProperty Panels {8E3867A5-8586-11D1-B16A-00C0F0283628} 
         NumPanels       =   1
         BeginProperty Panel1 {8E3867AB-8586-11D1-B16A-00C0F0283628} 
            AutoSize        =   2
            Object.Width           =   2831
            Text            =   "Estado: A la espera..."
            TextSave        =   "Estado: A la espera..."
         EndProperty
      EndProperty
   End
   Begin VB.CommandButton facturarButton 
      Caption         =   "Facturar!"
      Height          =   495
      Left            =   9600
      TabIndex        =   15
      Top             =   4680
      Width           =   1455
   End
   Begin VB.TextBox ivaTotalField 
      Alignment       =   1  'Right Justify
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   12
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   375
      Left            =   3960
      Locked          =   -1  'True
      TabIndex        =   14
      Text            =   "0.00"
      Top             =   4800
      Width           =   1695
   End
   Begin VB.TextBox totalField 
      Alignment       =   1  'Right Justify
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   12
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   375
      Left            =   840
      Locked          =   -1  'True
      TabIndex        =   12
      Text            =   "0.00"
      Top             =   4800
      Width           =   2295
   End
   Begin VB.CheckBox impresasChk 
      Caption         =   "Impresas S/N"
      Height          =   195
      Left            =   360
      TabIndex        =   10
      Top             =   480
      Width           =   1575
   End
   Begin VB.CommandButton selectNoneButton 
      Caption         =   "Nada"
      Height          =   315
      Left            =   840
      TabIndex        =   9
      ToolTipText     =   "Destildar todos los elementos"
      Top             =   720
      Width           =   735
   End
   Begin VB.CommandButton selectAllButton 
      Caption         =   "Todo"
      Height          =   315
      Left            =   120
      TabIndex        =   8
      ToolTipText     =   "Tildar todos los elementos"
      Top             =   720
      Width           =   735
   End
   Begin VB.CommandButton testCommButton 
      Caption         =   "TestComm"
      Height          =   495
      Left            =   10080
      TabIndex        =   7
      Top             =   0
      Width           =   975
   End
   Begin MSComctlLib.ListView comprobantesTable 
      Height          =   2415
      Left            =   120
      TabIndex        =   5
      Top             =   1080
      Width           =   10935
      _ExtentX        =   19288
      _ExtentY        =   4260
      View            =   3
      LabelEdit       =   1
      LabelWrap       =   -1  'True
      HideSelection   =   -1  'True
      Checkboxes      =   -1  'True
      FullRowSelect   =   -1  'True
      GridLines       =   -1  'True
      _Version        =   393217
      ForeColor       =   -2147483640
      BackColor       =   -2147483643
      BorderStyle     =   1
      Appearance      =   1
      NumItems        =   0
   End
   Begin VB.CommandButton filtrarButton 
      Caption         =   "Filtrar"
      Height          =   495
      Left            =   5760
      TabIndex        =   4
      Top             =   0
      Width           =   1095
   End
   Begin MSComCtl2.DTPicker fechaHastaField 
      Height          =   255
      Left            =   4080
      TabIndex        =   3
      Top             =   120
      Width           =   1455
      _ExtentX        =   2566
      _ExtentY        =   450
      _Version        =   393216
      Format          =   15925249
      CurrentDate     =   42094
   End
   Begin MSComCtl2.DTPicker fechaDesdeField 
      Height          =   255
      Left            =   1440
      TabIndex        =   1
      Top             =   120
      Width           =   1455
      _ExtentX        =   2566
      _ExtentY        =   450
      _Version        =   393216
      Format          =   15925249
      CurrentDate     =   42094
   End
   Begin MSComctlLib.ListView detalleTable 
      Height          =   1095
      Left            =   120
      TabIndex        =   6
      Top             =   3600
      Width           =   10935
      _ExtentX        =   19288
      _ExtentY        =   1931
      View            =   3
      LabelEdit       =   1
      LabelWrap       =   -1  'True
      HideSelection   =   -1  'True
      FullRowSelect   =   -1  'True
      GridLines       =   -1  'True
      _Version        =   393217
      ForeColor       =   -2147483640
      BackColor       =   -2147483643
      BorderStyle     =   1
      Appearance      =   1
      NumItems        =   0
   End
   Begin VB.Label Label6 
      Caption         =   "Pagina:"
      Height          =   375
      Left            =   3960
      TabIndex        =   20
      Top             =   720
      Width           =   735
   End
   Begin VB.Label cantidadField 
      Caption         =   "0"
      Height          =   255
      Left            =   2520
      TabIndex        =   17
      Top             =   720
      Width           =   1215
   End
   Begin VB.Label Label5 
      Caption         =   "Cantidad:"
      Height          =   255
      Left            =   1680
      TabIndex        =   16
      Top             =   720
      Width           =   855
   End
   Begin VB.Label Label4 
      Caption         =   "IVA:"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   255
      Left            =   3240
      TabIndex        =   13
      Top             =   4800
      Width           =   615
   End
   Begin VB.Label Label3 
      Caption         =   "Total:"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   255
      Left            =   120
      TabIndex        =   11
      Top             =   4800
      Width           =   615
   End
   Begin FiscalPrinterLibCtl.HASAR HASAR1 
      Left            =   7800
      OleObjectBlob   =   "frmComprobantes.frx":0000
      Top             =   0
   End
   Begin VB.Label Label2 
      Caption         =   "Fecha hasta:"
      Height          =   255
      Left            =   3000
      TabIndex        =   2
      Top             =   120
      Width           =   1215
   End
   Begin VB.Label Label1 
      Caption         =   "Fecha desde:"
      Height          =   255
      Left            =   360
      TabIndex        =   0
      Top             =   120
      Width           =   1215
   End
End
Attribute VB_Name = "frmComprobantes"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit
Private comprobantesRecord As ADODB.recordset
Private puntoVenta As String
Private log As New frmLog


Private Sub cierreZButton_Click()
    If MsgBox("Seguro?", vbYesNo + vbDefaultButton2, "Cierre Z") = vbNo Then
        Exit Sub
    End If
    inicializarImpresora
    HASAR1.ReporteZ
End Sub

Private Sub comprobantesTable_ItemClick(ByVal item As MSComctlLib.ListItem)
    Dim id As Long
    id = CLng(item.text)
    llenarListView obtenerLineasComprobante(id), detalleTable, 1, True
    calcularTotales
End Sub

Private Sub facturarButton_Click()
    If MsgBox("Estas completamente seguro?", vbYesNo + vbDefaultButton2, "Seguro?") = vbNo Then
        Exit Sub
    End If
    habilitarPantalla False
    
    progress.Value = 0
    
    Dim cantidad As Integer
    
    Dim item As ListItem
    For Each item In comprobantesTable.ListItems
        If item.Checked Then
            cantidad = cantidad + 1
        End If
    Next item
    
    progress.Max = cantidad
    
    inicializarImpresora
    
    
    For Each item In comprobantesTable.ListItems
        If item.Checked Then
            progress.Value = progress.Value + 1
            facturarComprobante CLng(item.text)
            
        End If
    Next item
    
    MsgBox "Completado exitosamente!"
    actualizadEstado "Trabajo finalizado"
    habilitarPantalla True
    comprobantesTable.ListItems.Clear
    HASAR1.Finalizar
End Sub

Private Sub habilitarPantalla(enable As Boolean)
    facturarButton.Enabled = enable
    comprobantesTable.Enabled = enable
    cierreZButton.Enabled = enable
    filtrarButton.Enabled = enable
End Sub
Private Sub actualizadEstado(estado As String)
    statusBar.Panels(1).text = estado
    frmLog.registrarLog estado
    DoEvents
End Sub
Private Sub facturarComprobante(id As Long)
    Dim compRecord As ADODB.recordset
    Set compRecord = obtenerComprobante(id)
    If compRecord.EOF Then
        Err.Raise 1, "Database", "No existe el comprobante!"
    End If
    Dim fechaFactura As Date
    fechaFactura = HASAR1.FechaHoraFiscal
    
    If compRecord.Fields("Id_Cond_Iva") = 2 Then
        HASAR1.DatosCliente compRecord.Fields("Cliente"), compRecord.Fields("DNI"), TIPO_DNI, CONSUMIDOR_FINAL
    ElseIf compRecord.Fields("Id_Cond_Iva") = 0 Then
        HASAR1.DatosCliente compRecord.Fields("Cliente"), compRecord.Fields("CUIT"), TIPO_CUIT, RESPONSABLE_INSCRIPTO
    End If
    
    Dim nroFactura As Long
    If compRecord.Fields("Letra") = "A" Then
        nroFactura = HASAR1.UltimaFactura
        HASAR1.AbrirComprobanteFiscal TICKET_FACTURA_A
    Else
        nroFactura = HASAR1.UltimoTicket
        HASAR1.AbrirComprobanteFiscal TICKET_FACTURA_B
    End If
    nroFactura = nroFactura + 1
    actualizadEstado "Abriendo comprobante fiscal " & "Comrpobante: " & id & " Factura: " & nroFactura
    
    
    Dim lineasRecord As ADODB.recordset
    Set lineasRecord = obtenerLineasComprobante(id)
    While Not lineasRecord.EOF
        HASAR1.ImprimirItem lineasRecord.Fields("Descripcion"), lineasRecord.Fields("cantidad"), Val(Replace(lineasRecord.Fields("ImporteUnit"), ",", ".")), lineasRecord.Fields("PorcIVA"), 0
        lineasRecord.MoveNext
    Wend
    HASAR1.CerrarComprobanteFiscal
    'Actualizo los datos en la tabla
    actualizadEstado "Registrando factura en base de datos..."
    registrarFactura id, puntoVenta, nroFactura, fechaFactura
    actualizadEstado "Factura registrada"
End Sub

Private Sub filtrarButton_Click()
If Not IsNumeric(tamPaginaField.text) Then
    MsgBox "Erorr en el tamaño de pagina", vbCritical
    Exit Sub
End If
    Set comprobantesRecord = obtenerComprobantes(fechaDesdeField.Value, fechaHastaField.Value, CBool(impresasChk.Value), CInt(tamPaginaField.text))

    llenarListView comprobantesRecord, comprobantesTable, 1, True
    
End Sub


Private Sub Form_Load()
    fechaDesdeField = Now
    fechaHastaField = Now
    
    frmLog.Show , Me
End Sub

Private Sub HASAR1_EventoImpresora(ByVal Flags As Long)

    Debug.Print HASAR1.DescripcionStatusImpresor(Flags)

    '// Falta la inteligencia del tratamiento del evento
    '//-------------------------------------------------
    Select Case Flags
        Case P_JOURNAL_PAPER_LOW, P_RECEIPT_PAPER_LOW:
            MsgBox "Falta papel"
        Case P_OFFLINE:
            Debug.Print "Impresor fuera de línea"
        Case P_PRINTER_ERROR:
            MsgBox "Error mecánico de impresor"
        Case Else:
            MsgBox "Otro bit de impresora"
    End Select

End Sub

Private Sub selectAllButton_Click()
    Dim item As ListItem
    For Each item In comprobantesTable.ListItems
        item.Checked = True
    Next item
End Sub

Private Sub selectNoneButton_Click()
    Dim item As ListItem
    For Each item In comprobantesTable.ListItems
        item.Checked = False
    Next item
End Sub

Private Sub calcularTotales()
    Dim item As ListItem
    Dim total As Double
    Dim cant As Long
    Dim iva As Double
    total = 0
    cant = 0
    iva = 0
    For Each item In comprobantesTable.ListItems
        If item.Checked Then
            total = total + Val(Replace(item.SubItems(2), ",", "."))
            iva = iva + Val(Replace(item.SubItems(3), ",", "."))
            cant = cant + 1
        End If
    Next item
    totalField.text = FormatNumber(total, 2, vbUseDefault, vbUseDefault, vbTrue)
    ivaTotalField.text = FormatNumber(iva, 2, vbUseDefault, vbUseDefault, vbTrue)
    cantidadField.Caption = cant
End Sub

'Inicializa la comunicacion con la impresora
Private Sub inicializarImpresora()
    actualizadEstado "Inicializando impresora..."
    HASAR1.Puerto = comPort
    HASAR1.Modelo = modeloFiscal
    HASAR1.Comenzar
    HASAR1.TratarDeCancelarTodo
    HASAR1.ObtenerDatosDeInicializacion
    puntoVenta = HASAR1.Respuesta(7)
End Sub


Private Sub testCommButton_Click()
    statusBar.Panels(1).text = "Estado: Probando comunicacion..."
    DoEvents
    HASAR1.Puerto = comPort
    HASAR1.Modelo = modeloFiscal
    HASAR1.Comenzar
    HASAR1.TratarDeCancelarTodo
    HASAR1.ObtenerDatosDeInicializacion
    MsgBox "Ultimo ticket:" + HASAR1.Respuesta(7) + "-" + Format(HASAR1.UltimoTicket, "00000000"), vbOKOnly, "Test!"
    HASAR1.Finalizar
    statusBar.Panels(1).text = "Estado: A la espera..."
    ' MsgBox HASAR1.Respuesta(7) ' Punto de venta
End Sub


