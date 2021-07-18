B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=7.95
@EndOfDesignText@
#Event: finish (Success as Boolean)

Sub Class_Globals
	Private mCallBack As Object
	Private mEvent As String
	
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(CallBack As Object, Event As String)
	mCallBack=CallBack
	mEvent=Event
End Sub


public Sub Zip(FilesTxt() As String, FileZip As String)
	Dim J As JavaObject = Me
	'J.InitializeContext
	J.RunMethod("ZipCompress", Array(FilesTxt,FileZip))
End Sub

public Sub unZip(FileZip As String, OutPutFolder As String)
	Dim J As JavaObject = Me
	'J.InitializeContext
	j.RunMethod("unZipDecompress", Array(FileZip,OutPutFolder))
End Sub

public Sub unZipList(FileZip As String) As List
	Dim J As JavaObject = Me
	'J.InitializeContext

	Dim L As List
	L=j.RunMethod("unZipList", Array(FileZip))

	Return l
End Sub

Private Sub uz_complete
	If SubExists(mCallBack,mEvent & "_finish") Then CallSubDelayed2(mCallBack,mEvent & "_finish",True)
End Sub

Private Sub uz_error
	If SubExists(mCallBack,mEvent & "_finish") Then CallSubDelayed2(mCallBack,mEvent & "_finish",False)
End Sub

'@Permissions(values={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})

#if java
import java.io.*;
//import java.util.*;
import java.util.zip.*;
import java.util.List;
import java.util.ArrayList;

import anywheresoftware.b4a.BA.Permissions;
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

#End If
