package cn.tzs.web.action.cargo;


import cn.tzs.domain.Contract;
import cn.tzs.domain.Export;
import cn.tzs.domain.ExportProduct;
import cn.tzs.exceotion.SysException;
import cn.tzs.export.webservice.Exception_Exception;
import cn.tzs.export.webservice.IEpService;
import cn.tzs.service.ContractService;
import cn.tzs.service.ExportProductService;
import cn.tzs.service.ExportService;
import cn.tzs.utils.Page;
import cn.tzs.utils.UtilFuns;
import cn.tzs.vo.ExportProductResult;
import cn.tzs.vo.ExportProductVo;
import cn.tzs.vo.ExportResult;
import cn.tzs.vo.ExportVo;
import cn.tzs.web.action.BaseAction;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Namespace("/cargo")
@Results({
        @Result(name = "contractList", location = "/WEB-INF/pages/cargo/export/jContractList.jsp"),
        //@Result(name = "toCView", location = "/WEB-INF/pages/cargo/contract/jContractView.jsp"),
        //@Result(name = "cPrint", location = "/WEB-INF/pages/cargo/export/jContractList.jsp"),
        @Result(name = "tocreate", location = "/WEB-INF/pages/cargo/export/jExportCreate.jsp"),
        @Result(name = "list", location = "/WEB-INF/pages/cargo/export/jExportList.jsp"),
        @Result(name = "toview", location = "/WEB-INF/pages/cargo/export/jExportView.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/cargo/export/jExportUpdate.jsp"),
        @Result(name = "tolist", location = "exportAction_list", type = "redirect")
})
public class ExportAction extends BaseAction implements ModelDriven<Export> {
//    webService  传统jaxws方式
//    @Autowired
//    private IEpService iEpService;      //webService接口

    @Autowired
    private ExportService exportService;
    @Autowired
    private ExportProductService exportProductService;
    @Autowired
    private ContractService contractService;
    private Export model = new Export();
    private Page page = new Page();

    @Action("exportAction_contractList")
    public String contractList() {
        //查询所有购销合同状态为 1 的数据
        org.springframework.data.domain.Page<Contract> jpaPage = contractService.findPage(new Specification<Contract>() {
            @Override
            public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), "1");
            }
        }, new PageRequest(page.getPageNo() - 1, page.getPageSize()));

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        page.setUrl("exportAction_contractList");

        super.push(page);
        return "contractList";
    }

//    @Action("contractAction_toview")
//    public String toCView() throws SysException {
////        Contract contract = contractService.findOne(model.getId());
////        super.push(contract);
//        throw new SysException("还没有这个功能哦");
////        return "toCView";
//    }
//
//    @Action("contractAction_print")
//    public String cPrint() throws SysException {
//        throw new SysException("还没有这个功能哦");
////        return "cPrint";
//    }

    //跳转到添加报运页面
    @Action("exportAction_tocreate")
    public String tocreate() {
        return "tocreate";
    }

    //添加海关报运单
    @Action("exportAction_insert")
    public String insert() {
        exportService.saveOrUpdate(model);
        return "tolist";
    }

    //查看所有报运单
    @Action("exportAction_list")
    public String list() {
        org.springframework.data.domain.Page<Export> jpaPage = exportService.findPage(
                null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        page.setUrl("exportAction_list");

        super.push(page);
        return "list";
    }

    //查看当报运单
    @Action("exportAction_toview")
    public String toview() throws SysException {
        Export export = exportService.findOne(model.getId());
        super.push(export);
        return "toview";
    }

    @Action("exportAction_toupdate")
    public String toupdate() throws SysException {
        Export export = exportService.findOne(model.getId());
        super.push(export);
        return "toupdate";
    }

    @Action("exportAction_getTableData")
    public String getTableData() throws SysException, IOException {
        Export export = exportService.findOne(model.getId());
        ArrayList<HashMap<String, Object>> returnList = new ArrayList<HashMap<String, Object>>();
        //获取报运单对象
        Set<ExportProduct> exportProducts = export.getExportProducts();
        for (ExportProduct exportProduct : exportProducts) {
            // "id", "productNo", "cnumber", "grossWeight", "netWeight", "sizeLength", "sizeWidth", "sizeHeight", "exPrice", "tax"
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", exportProduct.getId());
            map.put("productNo", exportProduct.getProductNo());
            map.put("cnumber", UtilFuns.convertNull(exportProduct.getCnumber()));
            map.put("grossWeight", UtilFuns.convertNull(exportProduct.getGrossWeight()));
            map.put("netWeight", UtilFuns.convertNull(exportProduct.getNetWeight()));
            map.put("sizeLength", UtilFuns.convertNull(exportProduct.getSizeLength()));
            map.put("sizeWidth", UtilFuns.convertNull(exportProduct.getSizeWidth()));
            map.put("sizeHeight", UtilFuns.convertNull(exportProduct.getSizeHeight()));
            map.put("exPrice", UtilFuns.convertNull(exportProduct.getExPrice()));
            map.put("tax", UtilFuns.convertNull(exportProduct.getTax()));
            returnList.add(map);
        }
        // 返回json数据
        String s = JSON.toJSONString(returnList);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(s.replaceAll("\"","'"));
        response.getWriter().write(s);

        return NONE;
    }

    //此方法用到的属性驱动
    private String[] mr_id;
    private String[] mr_changed;
    private Integer[] mr_cnumber;
    private Double[] mr_grossWeight;
    private Double[] mr_netWeight;
    private Double[] mr_sizeLength;
    private Double[] mr_sizeWidth;
    private Double[] mr_sizeHeight;
    private Double[] mr_exPrice;
    private Double[] mr_tax;

    @Action("exportAction_update")
    public String update() throws SysException {
        // 1.完成出口报运单修改
        Export export = exportService.findOne(model.getId());
        export.setInputDate(model.getInputDate());
        export.setLcno(model.getLcno());
        export.setConsignee(model.getConsignee());
        export.setShipmentPort(model.getShipmentPort());
        export.setDestinationPort(model.getDestinationPort());
        export.setTransportMode(model.getTransportMode());
        export.setPriceCondition(model.getPriceCondition());
        export.setMarks(model.getMarks());
        export.setRemark(model.getRemark());

        exportService.saveOrUpdate(export);

        // 2.完成出口报运单货物修改
        for (int i = 0; i < mr_changed.length; i++) {
            if (mr_changed[i].equals("1")) {
                ExportProduct ep = exportProductService.findOne(mr_id[i]);
                ep.setCnumber(mr_cnumber[i]);
                ep.setGrossWeight(mr_grossWeight[i]);
                ep.setNetWeight(mr_netWeight[i]);
                ep.setSizeLength(mr_sizeLength[i]);
                ep.setSizeWidth(mr_sizeWidth[i]);
                ep.setSizeHeight(mr_sizeHeight[i]);
                ep.setExPrice(mr_exPrice[i]);
                ep.setTax(mr_tax[i]);
                exportProductService.saveOrUpdate(ep);
            }
        }
        return "tolist";
    }

    @Action("exportAction_delete")
    public String delete() throws SysException {
        String[] ids = model.getId().split(", ");
        exportService.delete(ids);
        return "tolist";
    }

    @Action("exportAction_submit")
    public String submit() throws SysException {
        String[] ids = model.getId().split(", ");
        exportService.updateExportState(ids, 1);
        return "tolist";
    }

    @Action("exportAction_cancel")
    public String cancel() throws SysException {
        String[] ids = model.getId().split(", ");
        exportService.updateExportState(ids, 0);
        return "tolist";
    }

    /**
     * 海关报运
     *
     * @return
     * @throws SysException
     */
    @Action("exportAction_exportE")
    public String rsExportE() throws SysException, Exception_Exception {
//        Export export = exportService.findOne(model.getId());
//
//        ExportVo epVo = new ExportVo();
//        BeanUtils.copyProperties(export, epVo);
//        epVo.setExportId(export.getId());
//
//        Set<ExportProduct> exps = export.getExportProducts();
//        HashSet<ExportProductVo> expVos = new HashSet<ExportProductVo>();
//        for (ExportProduct exp : exps) {
//            ExportProductVo expVo = new ExportProductVo();
//            BeanUtils.copyProperties(exp, expVo);
//            expVo.setExportId(export.getId());
//            expVo.setExportProductId(exp.getId());
//            expVos.add(expVo);
//        }
//        epVo.setProducts(expVos);
//
//        WebClient webClient = WebClient.create("http://localhost:9080/webService_rs/ws/export/tzsVo");
//        webClient.post(epVo);
//
//        WebClient webClient1 = webClient.create("http://localhost:9080/webService_rs/ws/export/tzsVo/" + export.getId());
//        ExportResult exportResult = webClient1.get(ExportResult.class);
//
//
//        Export dbExport = exportService.findOne(exportResult.getExportId());
//        dbExport.setState(exportResult.getState());
//        dbExport.setRemark(exportResult.getRemark());
//        exportService.saveOrUpdate(dbExport);
//
//        Set<ExportProductResult> products = exportResult.getProducts();
//        for (ExportProductResult product : products) {
//            ExportProduct dbEp = exportProductService.findOne(product.getExportProductId());
//            dbEp.setTax(product.getTax());
//            exportProductService.saveOrUpdate(dbEp);
//        }

        return "tolist";
    }

    /**
     * 海关报运
     *
     * @return
     * @throws SysException
     */
//    @Action("exportAction_esExportE")
//    public String esExportE() throws SysException, Exception_Exception {
//        Export export = exportService.findOne(model.getId());
//        HashMap<String, Object> toJkMap = new HashMap<String, Object>();
//        toJkMap.put("exportId", export.getId());
////        toJkMap.put("state", export.getState());
////        toJkMap.put("remark", export.getRemark());
////        toJkMap.put("inputDate", export.getInputDate());
////        toJkMap.put("shipmentPort", export.getShipmentPort());
////        toJkMap.put("destinationPort", export.getDestinationPort());
////        toJkMap.put("transportMode", export.getTransportMode());
////        toJkMap.put("priceCondition", export.getPriceCondition());
////
//        Set<ExportProduct> exportProducts = export.getExportProducts();
//        ArrayList<HashMap<String, Object>> toMap = new ArrayList<HashMap<String, Object>>();
//        for (ExportProduct exportProduct : exportProducts) {
//            HashMap<String, Object> epMap = new HashMap<String, Object>();
//            epMap.put("exportProductId", exportProduct.getId());
//            epMap.put("factoryId", exportProduct.getFactory().getId());
//            epMap.put("productNo", exportProduct.getProductNo());
//            epMap.put("packingUnit", exportProduct.getPackingUnit());
//            epMap.put("cnumber", exportProduct.getCnumber());
//
//            toMap.add(epMap);
//        }
//        toJkMap.put("products", toMap);
//
//        String toJSONString = JSON.toJSONString(toJkMap);
//        System.out.println(toJSONString);
//        String returnJsonString = iEpService.exportE(toJSONString);
//        System.out.println("====================返回的json数据=====================" + returnJsonString);
//        /**
//         * { exportId:"", state:"", remark:"", products:[ { exportProductId:"",tax:"" }, { exportProductId:"", tax:"" } ] }
//         *
//         */
//        HashMap jkExport = JSON.parseObject(returnJsonString, HashMap.class);
//        Export dbExport = exportService.findOne(jkExport.get("exportId").toString());
//        dbExport.setState(Integer.parseInt(jkExport.get("state").toString()));
//        dbExport.setRemark(jkExport.get("remark").toString());
//        exportService.saveOrUpdate(dbExport);
//
//        List<HashMap> dbExportProducts = JSON.parseArray(jkExport.get("products").toString(), HashMap.class);
//        for (HashMap exportProduct : dbExportProducts) {
//            ExportProduct dbExportProduct = exportProductService.findOne(exportProduct.get("exportProductId").toString());
//            dbExportProduct.setTax(Double.parseDouble(exportProduct.get("tax").toString()));
//            exportProductService.saveOrUpdate(dbExportProduct);
//        }
//        return "tolist";
//    }


    //////////////////////////get/set/////////////////////////////////////
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Export getModel() {
        return model;
    }

    public void setModel(Export model) {
        this.model = model;
    }

    public String[] getMr_id() {
        return mr_id;
    }

    public void setMr_id(String[] mr_id) {
        this.mr_id = mr_id;
    }

    public String[] getMr_changed() {
        return mr_changed;
    }

    public void setMr_changed(String[] mr_changed) {
        this.mr_changed = mr_changed;
    }

    public Integer[] getMr_cnumber() {
        return mr_cnumber;
    }

    public void setMr_cnumber(Integer[] mr_cnumber) {
        this.mr_cnumber = mr_cnumber;
    }

    public Double[] getMr_grossWeight() {
        return mr_grossWeight;
    }

    public void setMr_grossWeight(Double[] mr_grossWeight) {
        this.mr_grossWeight = mr_grossWeight;
    }

    public Double[] getMr_netWeight() {
        return mr_netWeight;
    }

    public void setMr_netWeight(Double[] mr_netWeight) {
        this.mr_netWeight = mr_netWeight;
    }

    public Double[] getMr_sizeLength() {
        return mr_sizeLength;
    }

    public void setMr_sizeLength(Double[] mr_sizeLength) {
        this.mr_sizeLength = mr_sizeLength;
    }

    public Double[] getMr_sizeWidth() {
        return mr_sizeWidth;
    }

    public void setMr_sizeWidth(Double[] mr_sizeWidth) {
        this.mr_sizeWidth = mr_sizeWidth;
    }

    public Double[] getMr_sizeHeight() {
        return mr_sizeHeight;
    }

    public void setMr_sizeHeight(Double[] mr_sizeHeight) {
        this.mr_sizeHeight = mr_sizeHeight;
    }

    public Double[] getMr_exPrice() {
        return mr_exPrice;
    }

    public void setMr_exPrice(Double[] mr_exPrice) {
        this.mr_exPrice = mr_exPrice;
    }

    public Double[] getMr_tax() {
        return mr_tax;
    }

    public void setMr_tax(Double[] mr_tax) {
        this.mr_tax = mr_tax;
    }

}
