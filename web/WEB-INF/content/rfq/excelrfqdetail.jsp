<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.regex.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.pentair.showcase.catalog.entity.*" %>
<%@ page import="com.pentair.showcase.rfq.entity.*" %>
<%@ page import="com.pentair.showcase.common.entity.User" %>
<%@ page import="jxl.*" %>
<%@ page import="jxl.write.*" %>
<%
    response.setHeader("Content-disposition", "attachment; filename=Rfqs.xls");
    com.opensymphony.xwork2.util.ValueStack vs = (com.opensymphony.xwork2.util.ValueStack) request.getAttribute("struts.valueStack");
    org.springside.modules.orm.Page<Rfq> data = (org.springside.modules.orm.Page<Rfq>) vs.findValue("page");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat df = new DecimalFormat("#.##");

    List<Rfq> rfqs = data.getResult();

    WritableWorkbook book = Workbook.createWorkbook(response.getOutputStream());

    WritableSheet sheet = book.createSheet("RFQs", 0);
    sheet.addCell(new Label(0, 0, "RFQ 编号"));
    sheet.addCell(new Label(1, 0, "客户名称"));
    sheet.addCell(new Label(2, 0, "所属地区"));
    sheet.addCell(new Label(3, 0, "品牌"));
    sheet.addCell(new Label(4, 0, "产品"));
    sheet.addCell(new Label(5, 0, "产品经理"));
    sheet.addCell(new Label(6, 0, "销售工程师"));
    sheet.addCell(new Label(7, 0, "应用工程师"));
    sheet.addCell(new Label(8, 0, "报价工程师"));
    sheet.addCell(new Label(9, 0, "应用服务专员"));
    sheet.addCell(new Label(10, 0, "录入时间"));
    sheet.addCell(new Label(11, 0, "状态"));
//added 2013-03-25
    sheet.addCell(new Label(12, 0, "分配至应用工程师"));
    sheet.addCell(new Label(13, 0, "分配至报价工程师"));
    sheet.addCell(new Label(14, 0, "应用工程师重新提交至报价工程师"));
    sheet.addCell(new Label(15, 0, "报价工程师提交报价至销售工程师"));
    sheet.addCell(new Label(16, 0, "已下单"));
    sheet.addCell(new Label(17, 0, "产品编码"));
    sheet.addCell(new Label(18, 0, "产品名称"));
    sheet.addCell(new Label(19, 0, "青岛公开价(不含税不含运费)"));
    sheet.addCell(new Label(20, 0, "年需求量(台/套)"));
    sheet.addCell(new Label(21, 0, "年销售额(万元)"));

    int row = 1;
    for (Rfq rfq : rfqs) {
        sheet.addCell(new Label(0, row, rfq.getSn()));
        sheet.addCell(new Label(1, row, rfq.getCustomerName()));
        sheet.addCell(new Label(2, row, rfq.getOwnerSales().getArea().getName()));
        sheet.addCell(new Label(3, row, rfq.getSeries().getBrand().getName()));
        sheet.addCell(new Label(4, row, rfq.getSeries().getName()));
        sheet.addCell(new Label(5, row, rfq.getSeries().getOwnerPM().getName()));
        User sales = rfq.getOwnerSales();
        if (sales != null) {
            sheet.addCell(new Label(6, row, sales.getName()));
        }
        User app = rfq.getOwnerAPP();
        if (app != null) {
            sheet.addCell(new Label(7, row, app.getName()));
        }
        User ce = rfq.getOwnerCE();
        if (ce != null) {
            sheet.addCell(new Label(8, row, ce.getName()));
        }
        User cs = rfq.getOwnerCS();
        if (cs != null) {
            sheet.addCell(new Label(9, row, cs.getName()));
        }
        sheet.addCell(new Label(10, row, sdf.format(rfq.getInputTime())));
        sheet.addCell(new Label(11, row, rfq.getStatus().getName()));

        //added 2013-03-25
        List<RfqLog> logs = rfq.getRfqLogs();
        for (RfqLog log : logs) {
            String info = log.getInfo();
            Date date = log.getTheDate();
            if (info.indexOf("分配至应用工程师") >= 0) {
                sheet.addCell(new Label(12, row, sdf.format(date)));
            }
            if (info.indexOf("分配至报价工程师") >= 0) {
                sheet.addCell(new Label(13, row, sdf.format(date)));
            }
            if (info.indexOf("应用工程师重新提交至报价工程师") >= 0) {
                sheet.addCell(new Label(14, row, sdf.format(date)));
            }
            if (info.indexOf("报价工程师提交报价至销售工程师") >= 0) {
                sheet.addCell(new Label(15, row, sdf.format(date)));
            }
            if (info.indexOf("已下单") >= 0) {
                sheet.addCell(new Label(16, row, sdf.format(date)));
            }
        }

        List<RfqDetail> products = rfq.getRfqDetails();
        if (products.size() > 0) {
            Series series = rfq.getSeries();
            for (RfqDetail product : products) {

                sheet.addCell(new Label(17, row, product.getProductNo()));
                sheet.addCell(new Label(18, row, product.getProductName()));

                Float materialCost = product.getMaterialCost();
                Float loh = product.getLoh();
                if (materialCost != null && loh != null) {
                    sheet.addCell(new Label(19, row, df.format((materialCost + loh) / (1 - series.getTargetProfit() / 100) / (1 - series.getDiscountRate() / 100))));
                }

                String require = product.getQuantityYear();
                String sale = product.getSaleAmountYear();

                double requireYear = 0, saleAmount = 0;
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d+(\\.\\d+)?");
                if (require != null) {
                    Matcher matcher = pattern.matcher(new StringBuilder(require));
                    if (matcher.find()) {
                        requireYear = Double.parseDouble(matcher.group(0));
                    }
                    sheet.addCell(new Label(20, row, df.format(requireYear)));
                }

                if (sale != null) {
                    Matcher matcher = pattern.matcher(new StringBuilder(sale));
                    if (matcher.find()) {
                        saleAmount = Double.parseDouble(matcher.group(0));
                    }
                    sheet.addCell(new Label(21, row, df.format(saleAmount)));
                }

                row++;
            }
        }

        row++;
    }

    CellView cellView = new CellView();
    cellView.setSize(250 * 20);
    sheet.setColumnView(0, cellView);
    sheet.setColumnView(1, cellView);

    book.write();
    book.close();
%>