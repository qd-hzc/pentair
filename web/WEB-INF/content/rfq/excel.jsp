<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.pentair.showcase.rfq.entity.RfqDetail" %>
<%@ page import="jxl.*" %>
<%@ page import="jxl.write.*" %>
<%
    response.setHeader("Content-disposition", "attachment; filename=ProductNo.xls");
    com.opensymphony.xwork2.util.ValueStack vs = (com.opensymphony.xwork2.util.ValueStack) request.getAttribute("struts.valueStack");
    org.springside.modules.orm.Page<RfqDetail> data = (org.springside.modules.orm.Page<RfqDetail>) vs.findValue("page");

    List<RfqDetail> details = data.getResult();

    WritableWorkbook book = Workbook.createWorkbook(response.getOutputStream());

    WritableSheet sheet = book.createSheet("ProductNo", 0);
    sheet.addCell(new Label(0, 0, "产品编码"));
    sheet.addCell(new Label(1, 0, "产品名称"));

    int row = 1;
    for (RfqDetail detail : details) {
        sheet.addCell(new Label(0, row, detail.getProductNo()));
        sheet.addCell(new Label(1, row, detail.getProductName()));
        row++;
    }

    CellView cellView = new CellView();
    cellView.setSize(250 * 20);
    sheet.setColumnView(0, cellView);
    sheet.setColumnView(1, cellView);

    book.write();
    book.close();
%>