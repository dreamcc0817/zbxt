package com.bonc.ioc.model;

/**
 * @Author: lsd
 * @Date: 2018/11/17 9:54
 * @Description:
 */
public class DocumentInfo {

    //主键ID
    private  String docId;

    //文档名称
    private  String docName;

    //文档类型
    private  String docType;

    //文档编码
    private  String docCode;

    //文档描述
    private  String docDesc;

    //文档存储路径
    private  String docSaveurl;

    //文档创建人员
    private  String docCreator;

    //文档创建时间
    private  String docCreatetime;

    //更新人员
    private  String docUpdator;


    private  String docUpdatetime;


    public  String  getDocId(){
        return  this.docId;
    };
    public  void  setDocId(String docId){
        this.docId=docId;
    }

    public  String  getDocName(){
        return  this.docName;
    };
    public  void  setDocName(String docName){
        this.docName=docName;
    }

    public  String  getDocType(){
        return  this.docType;
    };
    public  void  setDocType(String docType){
        this.docType=docType;
    }

    public  String  getDocCode(){
        return  this.docCode;
    };
    public  void  setDocCode(String docCode){
        this.docCode=docCode;
    }

    public  String  getDocDesc(){
        return  this.docDesc;
    };
    public  void  setDocDesc(String docDesc){
        this.docDesc=docDesc;
    }

    public  String  getDocSaveurl(){
        return  this.docSaveurl;
    };
    public  void  setDocSaveurl(String docSaveurl){
        this.docSaveurl=docSaveurl;
    }

    public  String  getDocCreator(){
        return  this.docCreator;
    };
    public  void  setDocCreator(String docCreator){
        this.docCreator=docCreator;
    }

    public  String  getDocCreatetime(){
        return  this.docCreatetime;
    };
    public  void  setDocCreatetime(String docCreatetime){
        this.docCreatetime=docCreatetime;
    }

    public  String  getDocUpdator(){
        return  this.docUpdator;
    };
    public  void  setDocUpdator(String docUpdator){
        this.docUpdator=docUpdator;
    }

    public  String  getDocUpdatetime(){
        return  this.docUpdatetime;
    };
    public  void  setDocUpdatetime(String docUpdatetime){
        this.docUpdatetime=docUpdatetime;
    }
}
