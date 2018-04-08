package cn.tzs.web.action.cargo;

import cn.tzs.domain.ContractProduct;
import cn.tzs.service.ContractProductService;
import cn.tzs.utils.DownloadUtil;
import cn.tzs.utils.UtilFuns;
import cn.tzs.web.action.BaseAction;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@Namespace("/cargo")
@Results({
        @Result(name = "toedit", location = "/WEB-INF/pages/cargo/outproduct/jOutProduct.jsp")
})
public class OutProductAction extends BaseAction {

    @Autowired
    private ContractProductService contractProductService;
    private String inputDate;


    @Action("outProductAction_toedit")
    public String toedit() {
        return "toedit";
    }

    //使用模板操作打印
    @Action("outProductAction_print")
    public String print() throws IOException, ParseException {
        //获取模板文件
        String filePath = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tOUTPRODUCT.xls");
        //String filePath = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tOUTPRODUCT.xlsx");

        Workbook book = new HSSFWorkbook(new FileInputStream(filePath));
        //Workbook book = new XSSFWorkbook(new FileInputStream(filePath));
        Sheet sheet = book.getSheetAt(0);

        //定义一个行数
        int rowIndex = 0;

        //标题行
        Row titleRow = sheet.getRow(rowIndex++);
        Cell titleCell = titleRow.getCell(1);
        String filename = inputDate.replace("-0", "-").replace("-", "年") + "月份出货表";
        titleCell.setCellValue(filename);

        //小标题行  略过，一直不变
        rowIndex++;

        //数据行显示
        //获取主要内容中每个单元格的格式
        Row contentRow = sheet.getRow(rowIndex);
        CellStyle style01 = contentRow.getCell(1).getCellStyle();
        CellStyle style02 = contentRow.getCell(2).getCellStyle();
        CellStyle style03 = contentRow.getCell(3).getCellStyle();
        CellStyle style04 = contentRow.getCell(4).getCellStyle();
        CellStyle style05 = contentRow.getCell(5).getCellStyle();
        CellStyle style06 = contentRow.getCell(6).getCellStyle();
        CellStyle style07 = contentRow.getCell(7).getCellStyle();
        CellStyle style08 = contentRow.getCell(8).getCellStyle();
        //充填数据
        List<ContractProduct> list = contractProductService.findCpByShipTime(inputDate);
        for (ContractProduct contractProduct : list) {
            Row row = sheet.createRow(rowIndex++);
            row.setHeightInPoints(26);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(contractProduct.getContract().getCustomName());
            cell1.setCellStyle(style01);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(contractProduct.getContract().getContractNo());
            cell2.setCellStyle(style02);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(contractProduct.getProductNo());
            cell3.setCellStyle(style03);

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(contractProduct.getCnumber());
            cell4.setCellStyle(style04);

            Cell cell5 = row.createCell(5);
            cell5.setCellValue(contractProduct.getFactoryName());
            cell5.setCellStyle(style05);

            Cell cell6 = row.createCell(6);
            cell6.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));
            cell6.setCellStyle(style06);

            Cell cell7 = row.createCell(7);
            cell7.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));
            cell7.setCellStyle(style07);

            Cell cell8 = row.createCell(8);
            cell8.setCellValue(contractProduct.getContract().getTradeTerms());
            cell8.setCellStyle(style08);


        }

        //进行文件输出
        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        book.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream, ServletActionContext.getResponse(), filename + ".xls");
        //downloadUtil.download(byteArrayOutputStream, ServletActionContext.getResponse(), filename + ".xlsx");
        return NONE;
    }

    //未使用模板，直接操作
    @Action("outProductAction_OldPrint")
    public String OldPrint() throws IOException, ParseException {

        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet();

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
        //设置列宽
        sheet.setColumnWidth(1, 26 * 256);
        sheet.setColumnWidth(2, 12 * 256);
        sheet.setColumnWidth(3, 26 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 12 * 256);
        sheet.setColumnWidth(6, 12 * 256);
        sheet.setColumnWidth(7, 12 * 256);
        sheet.setColumnWidth(8, 12 * 256);

        //定义一个行数
        int rowIndex = 0;

        //标题行
        Row titleRow = sheet.createRow(rowIndex++);
        Cell titleCell = titleRow.createCell(1);
        String filename = inputDate.replace("-0", "-").replace("-", "年") + "月份出货表";
        titleCell.setCellValue(filename);
        titleCell.setCellStyle(bigTitle(book));
        //设置高度
        sheet.setDefaultRowHeightInPoints(36);

        //小标题行
        String[] titleStrs = {"客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款"};
        Row smailTitle = sheet.createRow(rowIndex++);
        smailTitle.setHeightInPoints(26);
        for (int i = 0; i < titleStrs.length; i++) {
            Cell smailCell = smailTitle.createCell(i + 1);
            smailCell.setCellValue(titleStrs[i]);
            smailCell.setCellStyle(title(book));
        }

        //数据
        List<ContractProduct> list = contractProductService.findCpByShipTime(inputDate);
        for (ContractProduct contractProduct : list) {
            Row row = sheet.createRow(rowIndex++);
            row.setHeightInPoints(26);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(contractProduct.getContract().getCustomName());
            cell1.setCellStyle(text(book));

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(contractProduct.getContract().getContractNo());
            cell2.setCellStyle(text(book));

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(contractProduct.getProductNo());
            cell3.setCellStyle(text(book));

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(contractProduct.getCnumber());
            cell4.setCellStyle(text(book));

            Cell cell5 = row.createCell(5);
            cell5.setCellValue(contractProduct.getFactoryName());
            cell5.setCellStyle(text(book));

            Cell cell6 = row.createCell(6);
            cell6.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));
            cell6.setCellStyle(text(book));

            Cell cell7 = row.createCell(7);
            cell7.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));
            cell7.setCellStyle(text(book));

            Cell cell8 = row.createCell(8);
            cell8.setCellValue(contractProduct.getContract().getTradeTerms());
            cell8.setCellStyle(text(book));


        }

        //进行文件输出
        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServletResponse response = ServletActionContext.getResponse();
        book.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream, response, filename + ".xls");
        return NONE;
    }

    //大标题的样式
    public CellStyle bigTitle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);                    //字体加粗

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_CENTER);                    //横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);        //纵向居中

        return style;
    }

    //小标题的样式
    public CellStyle title(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_CENTER);                    //横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);        //纵向居中

        style.setBorderTop(CellStyle.BORDER_THIN);                    //上细线
        style.setBorderBottom(CellStyle.BORDER_THIN);                //下细线
        style.setBorderLeft(CellStyle.BORDER_THIN);                    //左细线
        style.setBorderRight(CellStyle.BORDER_THIN);                //右细线

        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 10);

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_LEFT);                    //横向居左
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);        //纵向居中

        style.setBorderTop(CellStyle.BORDER_THIN);                    //上细线
        style.setBorderBottom(CellStyle.BORDER_THIN);                //下细线
        style.setBorderLeft(CellStyle.BORDER_THIN);                    //左细线
        style.setBorderRight(CellStyle.BORDER_THIN);                //右细线

        return style;
    }


    /////////////////////////get/set/////////////////////////////


    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }
}
