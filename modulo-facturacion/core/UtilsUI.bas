Attribute VB_Name = "UtilsUI"
Option Explicit

Public Sub llenarListView(recordset As recordset, _
                          ByRef litview As ListView, _
                          Optional tooltipSubItem As Integer, _
                          Optional autoajusteColumnas As Boolean)

    Dim cantCols As Integer

    Dim colX     As ColumnHeader ' Declare variable.

    cantCols = recordset.Fields.Count
    
    litview.ListItems.Clear
    litview.ColumnHeaders.Clear
    
    Dim intX As Integer ' Counter variable.
    
    For intX = 0 To cantCols - 1
        Set colX = litview.ColumnHeaders.Add()
        colX.text = recordset.Fields(intX).Name
        If autoajusteColumnas Then
            colX.Width = litview.Width / cantCols
        End If
    Next intX

    Dim fila As ListItem

    Do While Not recordset.EOF
        Set fila = litview.ListItems.Add(, , recordset.Fields(0))

        Dim I As Integer

        For I = 1 To litview.ColumnHeaders.Count - 1
            fila.SubItems(I) = IIf(IsNull(recordset.Fields(I)), "0", recordset.Fields(I))

            If I = tooltipSubItem Then
                fila.ToolTipText = recordset.Fields(I)
            End If
            
        Next I

        recordset.MoveNext
    Loop

End Sub

Public Sub seleccionarTodo(text As TextBox)
    text.SelStart = 0
    text.SelLength = Len(text.text)
End Sub


