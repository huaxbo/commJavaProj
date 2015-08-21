package com.automic.gxpt.ws.rev.intr;

public class ShareDataPortTypeProxy implements com.automic.gxpt.ws.rev.intr.ShareDataPortType {
  private String _endpoint = null;
  private com.automic.gxpt.ws.rev.intr.ShareDataPortType shareDataPortType = null;
  
  public ShareDataPortTypeProxy() {
    _initShareDataPortTypeProxy();
  }
  
  public ShareDataPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initShareDataPortTypeProxy();
  }
  
  private void _initShareDataPortTypeProxy() {
    try {
      shareDataPortType = (new com.automic.gxpt.ws.rev.intr.ShareDataLocator()).getshareDataHttpPort();
      if (shareDataPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)shareDataPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)shareDataPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (shareDataPortType != null)
      ((javax.xml.rpc.Stub)shareDataPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.automic.gxpt.ws.rev.intr.ShareDataPortType getShareDataPortType() {
    if (shareDataPortType == null)
      _initShareDataPortTypeProxy();
    return shareDataPortType;
  }
  
  public java.lang.String receiveRemoteData(java.lang.String in0) throws java.rmi.RemoteException{
    if (shareDataPortType == null)
      _initShareDataPortTypeProxy();
    return shareDataPortType.receiveRemoteData(in0);
  }
  
  public java.lang.String receive(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (shareDataPortType == null)
      _initShareDataPortTypeProxy();
    return shareDataPortType.receive(in0, in1);
  }
  
  
}