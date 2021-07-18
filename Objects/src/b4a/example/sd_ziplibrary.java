package b4a.example;

import java.io.*;
import java.util.zip.*;
import java.util.List;
import java.util.ArrayList;
import anywheresoftware.b4a.BA.Permissions;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class sd_ziplibrary extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.sd_ziplibrary");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.sd_ziplibrary.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public Object _v0 = null;
public String _vv1 = "";
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Private mCallBack As Object";
_v0 = new Object();
 //BA.debugLineNum = 5;BA.debugLine="Private mEvent As String";
_vv1 = "";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _callback,String _event) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 10;BA.debugLine="Public Sub Initialize(CallBack As Object, Event As";
 //BA.debugLineNum = 11;BA.debugLine="mCallBack=CallBack";
_v0 = _callback;
 //BA.debugLineNum = 12;BA.debugLine="mEvent=Event";
_vv1 = _event;
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public String  _v5(String _filezip,String _outputfolder) throws Exception{
anywheresoftware.b4j.object.JavaObject _j = null;
 //BA.debugLineNum = 22;BA.debugLine="public Sub unZip(FileZip As String, OutPutFolder A";
 //BA.debugLineNum = 23;BA.debugLine="Dim J As JavaObject = Me";
_j = new anywheresoftware.b4j.object.JavaObject();
_j.setObject((java.lang.Object)(this));
 //BA.debugLineNum = 25;BA.debugLine="j.RunMethod(\"unZipDecompress\", Array(FileZip,OutP";
_j.RunMethod("unZipDecompress",new Object[]{(Object)(_filezip),(Object)(_outputfolder)});
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _v6(String _filezip) throws Exception{
anywheresoftware.b4j.object.JavaObject _j = null;
anywheresoftware.b4a.objects.collections.List _l = null;
 //BA.debugLineNum = 28;BA.debugLine="public Sub unZipList(FileZip As String) As List";
 //BA.debugLineNum = 29;BA.debugLine="Dim J As JavaObject = Me";
_j = new anywheresoftware.b4j.object.JavaObject();
_j.setObject((java.lang.Object)(this));
 //BA.debugLineNum = 32;BA.debugLine="Dim L As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 33;BA.debugLine="L=j.RunMethod(\"unZipList\", Array(FileZip))";
_l.setObject((java.util.List)(_j.RunMethod("unZipList",new Object[]{(Object)(_filezip)})));
 //BA.debugLineNum = 35;BA.debugLine="Return l";
if (true) return _l;
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return null;
}
public String  _uz_complete() throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Private Sub uz_complete";
 //BA.debugLineNum = 39;BA.debugLine="If SubExists(mCallBack,mEvent & \"_finish\") Then C";
if (__c.SubExists(ba,_v0,_vv1+"_finish")) { 
__c.CallSubDelayed2(ba,_v0,_vv1+"_finish",(Object)(__c.True));};
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public String  _uz_error() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Private Sub uz_error";
 //BA.debugLineNum = 43;BA.debugLine="If SubExists(mCallBack,mEvent & \"_finish\") Then C";
if (__c.SubExists(ba,_v0,_vv1+"_finish")) { 
__c.CallSubDelayed2(ba,_v0,_vv1+"_finish",(Object)(__c.False));};
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public String  _v7(String[] _filestxt,String _filezip) throws Exception{
anywheresoftware.b4j.object.JavaObject _j = null;
 //BA.debugLineNum = 16;BA.debugLine="public Sub Zip(FilesTxt() As String, FileZip As St";
 //BA.debugLineNum = 17;BA.debugLine="Dim J As JavaObject = Me";
_j = new anywheresoftware.b4j.object.JavaObject();
_j.setObject((java.lang.Object)(this));
 //BA.debugLineNum = 19;BA.debugLine="J.RunMethod(\"ZipCompress\", Array(FilesTxt,FileZip";
_j.RunMethod("ZipCompress",new Object[]{(Object)(_filestxt),(Object)(_filezip)});
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
//import java.util.*;

@Permissions(values={"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_NETWORK_STATE"})


    public void ZipCompress(String[] txtFile, String zipFile) throws IOException {
    try
    {
      ZipOutputStream out = new ZipOutputStream (new BufferedOutputStream(new FileOutputStream(zipFile)));
      
	  for (int i = 0; i<txtFile.length; i++)
		{
      
	  	byte[] data = new byte[4096]; 
      	BufferedInputStream in = new BufferedInputStream (new FileInputStream(txtFile[i]));
      	int count;
	  	out.putNextEntry(new ZipEntry(txtFile[i].substring(txtFile[i].lastIndexOf("\\") + 1)));
      	while((count = in.read(data,0,4096)) != -1)
      		{ 
        	out.write(data, 0, count);
      		}
	  	in.close();
	  	out.flush();
	  	out.closeEntry();
	  
	  	}
      out.close();

      // conferma della compressione 
      //System.out.println("File zippato con successo");
	  ba.raiseEvent(null, "uz_complete");
    }
    catch(Exception e)
    {
      e.printStackTrace();
	  ba.raiseEvent(null, "uz_error");
    }
    
   
    }

 public void unZipDecompress(String zipFile, String outputFolder){

     byte[] buffer = new byte[1024];

     try{

    	//create output directory is not exists
    	File folder = new File(outputFolder);
    	if(!folder.exists()){
    		folder.mkdir();
    	}

    	//get the zip file content
    	ZipInputStream zis =
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();

     	while(ze!=null){

    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);

           //System.out.println("file unzip : "+ newFile.getAbsoluteFile());
		   //System.out.println("file : "+ outputFolder + File.separator + fileName);

           //create all non exists folders
           //else you will hit FileNotFoundException for compressed folder
           new File(newFile.getParent()).mkdirs();

		   if (!fileName.endsWith("/")) {
           		FileOutputStream fos = new FileOutputStream(newFile);

           		int len;
           		while ((len = zis.read(buffer)) > 0) {
       		  		fos.write(buffer, 0, len);
              		}
           		fos.close();
				}
           ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	//System.out.println("Done Unzip");
		ba.raiseEvent(null, "uz_complete");
    }catch(IOException ex){
       ex.printStackTrace();
	   ba.raiseEvent(null, "uz_error");
    }
   }

 public List unZipList(String zipFile){
 
	List<String> l = new ArrayList<String>();
	
	try{

    	//get the zip file content
    	ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();

     	while(ze!=null){
			String fileName = ze.getName();
			//File newFile = new File(outputFolder + File.separator + fileName);
			l.add(fileName);
            ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	//System.out.println("Done List");
		ba.raiseEvent(null, "uz_complete");
    }catch(IOException ex){
       ex.printStackTrace();
	   ba.raiseEvent(null, "uz_error");
    }
	return l;
   }

}
