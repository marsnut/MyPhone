package com.example.myphone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class RpcActivity extends Activity
{
  Button mButton;
  EditText mEdit1;
  EditText mEdit2;
  EditText mEdit3;
  EditText mEdit4;
  EditText mEdit5;
  private String path="http://xmlrpc.blog.roodo.com/cgi-bin/mt/mt-xmlrpc.cgi";
  private String method="metaWeblog.newPost";
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rpc);

    mEdit1=(EditText)findViewById(R.id.blogId);
    mEdit2=(EditText)findViewById(R.id.blogAccount);
    mEdit3=(EditText)findViewById(R.id.blogPwd);
    mEdit4=(EditText)findViewById(R.id.artTitle);
    mEdit5=(EditText)findViewById(R.id.artContent);  
    mButton=(Button)findViewById(R.id.myButton);
    
    mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        String blogId=mEdit1.getText().toString();
        String account=mEdit2.getText().toString();
        String pwd=mEdit3.getText().toString();
        String title=mEdit4.getText().toString();
        String content=mEdit5.getText().toString();
        
        if(blogId.equals("")||account.equals("")||pwd.equals("")||
           title.equals("")||content.equals(""))
        {
          showDialog("不能为空");
        }
        else
        {
          String outS=getPostString(method,blogId,account,pwd,title,content);
          String re=sendPost(outS);
          showDialog(re);
        }
      }
    });
  }
  
  private String sendPost(String outString)
  {
    HttpURLConnection conn=null;
    String result="";
    URL url = null;   
    try
    {
      url = new URL(path);
      conn = (HttpURLConnection)url.openConnection();
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "text/xml");
      conn.setRequestProperty("Charset", "UTF-8");
      
      OutputStreamWriter out = 
        new OutputStreamWriter(conn.getOutputStream(), "utf-8");
      out.write(outString);
      out.flush();
      out.close();
      result=parseXML(conn.getInputStream());
      conn.disconnect();
    }
    catch(Exception e)
    {
      conn.disconnect();
      e.printStackTrace();
      showDialog(""+e);
    }
    return result;
  }
  
  private String parseXML(InputStream is)
  {
    String result="";
    Document doc = null;
    try
    {
      DocumentBuilderFactory dbf=
        DocumentBuilderFactory.newInstance();
      DocumentBuilder db=dbf.newDocumentBuilder();
      doc = db.parse(is);
      doc.getDocumentElement().normalize();
      int fault=doc.getElementsByTagName("fault").getLength();
      if(fault>0)
      {
        result+="error!\n";
        NodeList nList1=doc.getElementsByTagName("int");
        for (int i = 0; i < nList1.getLength(); ++i)
        {
          String errCode=nList1.item(i).getChildNodes().item(0)
                         .getNodeValue();
          result+="错误码"+errCode+"\n";
        }
        NodeList nList2=doc.getElementsByTagName("string");
        for (int i = 0; i < nList2.getLength(); ++i)
        {
          String errString=nList2.item(i).getChildNodes().item(0)
                           .getNodeValue();
          result+="错误信息"+errString+"\n";
        }
      }
      else
      {
        NodeList nList=doc.getElementsByTagName("string");
        for (int i = 0; i < nList.getLength(); ++i)
        {
          String artId=nList.item(i).getChildNodes().item(0)
                       .getNodeValue();
          result+="artID:"+artId+"";
        }
      }
    }
    catch (Exception ioe) 
    {
      showDialog(""+ioe);
    }
    return result;
  }
  
  private String getPostString(String method,String blogId,
      String account,String pwd,String title,String content)
  {
    String s="";
    s+="<methodCall>";
    s+="<methodName>"+method+"</methodName>";
    s+="<params>";
    s+="<param><value><string>"+blogId+"</string></value></param>";
    s+="<param><value><string>"+account+"</string></value></param>";
    s+="<param><value><string>"+pwd+"</string></value></param>";
    s+="<param><value><struct>";
    s+="<member><name>title</name>" +
       "<value><string>"+title+"</string></value></member>";
    s+="<member><name>description</name>" +
       "<value><string>"+content+"</string></value></member>";
    s+="</struct></value></param>";
    s+="<param><value><boolean>1</boolean></value></param>";
    s+="</params>";
    s+="</methodCall>";

    return s;
  }
  
  private void showDialog(String mess)
  {
    new AlertDialog.Builder(RpcActivity.this).setTitle("Message")
    .setMessage(mess)
    .setNegativeButton("go", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int which)
      {          
      }
    })
    .show();
  }
}
