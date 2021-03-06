package com.pentair.showcase.unit.report;

import static org.junit.Assert.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.junit.Test;
import com.pentair.showcase.report.ExcelExportAction;
import org.springside.modules.utils.reflection.ReflectionUtils;

/**
 * 测试ExcelExportAction测试,演示Excel的读取.
 *
 * @author calvin
 */
public class ExcelExportActionTest {

    @Test
    public void test() throws Exception {
        ExcelExportAction action = new ExcelExportAction();
        Workbook wb = (Workbook) ReflectionUtils.invokeMethod(action, "exportExcelWorkbook", null, null);

        //按照Cell名称取得Cell,读取Cell的数值
        CellReference cr = new CellReference("B3");
        Cell cell1970 = wb.getSheetAt(0).getRow(cr.getRow()).getCell(cr.getCol());
        assertEquals(-0.068, cell1970.getNumericCellValue(), 0.01);

        //按照Cell的坐标取得Cell,读取Cell的公式计算结果.
        Cell cellTotal = wb.getSheetAt(0).getRow(32).getCell(1);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        assertEquals(2.373, evaluator.evaluate(cellTotal).getNumberValue(), 0.01);
    }
}
