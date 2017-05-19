package com.unittest.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLReport {
  public static String reportFileName = "unknown";
  public static int sNo=0;
  public static void createFolder(String strDirectoy) {
    boolean success = (new File(strDirectoy)).mkdirs();
    if (success) {
    }
  }
  public static void createHTMLTemplate(String environment, String strClassName) throws IOException {
    File tempFolder = new File("./junit");
    createFolder(tempFolder.getCanonicalPath().toString());
    reportFileName=tempFolder.getCanonicalPath().toString()+"//"+strClassName+"UnitTestReport.html";
    File file = new File(reportFileName);
    boolean blnCreated = false;
    try {
      blnCreated = file.createNewFile();
    } catch (IOException ioe) {
      System.out.println("Error while creating a new empty file :" + ioe);
    }
    System.out.println("Was file " + file.getPath() + " created ? : " + blnCreated);
    BufferedWriter writer = null;
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write("<html><body>");
      bw.write("<title>Results</title>");
      bw.write(
          "</title><BODY bgcolor=White><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
      bw.write(
          "<table id=check1><TR COLS=2><TD WIDTH=20%></TD><TD WIDTH=10% align=Right><TD WIDTH=10% align=Right></TD></TABLE>");
      bw.write(
          "<br/><table border=\"0\" width=\"100%\" height=\"45\" border-spacing: 0px bgcolor=\"#728139\" style=\"font-family:Copperplate Gothic Bold; font-size:25px; color: #fffbbf;\"><tr height=\"21\"><td valign=\"center\" align=\"center\" width=\"80%\"><p align=\"center\" style=\"font-family: Copperplate Gothic Bold\"> Your Own Project Unit Test Execution Report</p></td></tr></table><br/><br/>");

      bw.write(
          "<table><TR COLS=4><TD BGCOLOR=#D8BFD8 WIDTH=5%><FONT FACE=VERDANA COLOR=Black SIZE=2><B> Project Name <DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=#00008B SIZE=2><B> Your Own Project <DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD  WIDTH=10%></TD><TD  WIDTH=10%></TD></TR>");
      bw.write(
          "<TR COLS=1><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=Black SIZE=2><B> Enviornment <DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=#00008B SIZE=2><B> "
              + environment
              + " <DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD  WIDTH=10%></TD><TD  WIDTH=10%></TD></TR>");
      bw.write(
          "<TR COLS=1><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=Black SIZE=2><B> User <DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=#00008B SIZE=2><B>"+ System.getProperty("user.name") +"<DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD  WIDTH=10%></TD><TD  WIDTH=10%></TD></TR>");
      bw.write(
          "<TR COLS=1><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=Black SIZE=2><B> Date&Time <DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD BGCOLOR=#D8BFD8 WIDTH=10%><FONT FACE=VERDANA COLOR=#00008B SIZE=2><B>"+ getCurrentDateTime("dd-MMM-yyyy hh:mm:ss.SSS") +"<DIV ALIGN=RIGHT></DIV></B></FONT></TD><TD  WIDTH=10%></TD><TD  WIDTH=10%></TR></TD></table>");

      bw.write("<table width=\"100%\"><TABLEBORDER=0 BGCOLOR=YELLOW CELLPADDING=3 CELLSPACING=1 WIDTH=20%>");
      bw.write(
          "<TR COLS=5><TD BGCOLOR=#993300 WIDTH=3%><FONT FACE=VERDANA COLOR=White SIZE=1><center><B> SNo </B><center></FONT></TD>");
      bw.write(
          "<TD BGCOLOR=#993300 WIDTH=30%><FONT FACE=VERDANA COLOR=White SIZE=1.5><B> TestCaseName <B/></FONT></TD>");
      bw.write(
          "<TD BGCOLOR=#993300 WIDTH=10%><FONT FACE=VERDANA COLOR=White SIZE=1.5><B> Status </B></FONT></TD>");
      bw.write(
          "<TD BGCOLOR=#993300 WIDTH=60%><FONT FACE=VERDANA COLOR=White SIZE=1.5><B> Failure Details </B></FONT></TD>");
      bw.close();
    } catch (IOException e) {
      System.out.println("Exception Occured" + e.getMessage());
    } finally {
      try {
        if (writer != null)
          writer.close();
      } catch (IOException e) {
      }
    }
  }

  public static void reportPass(String strTestCaseName, String strStatus ) {
    BufferedWriter bw = null;
    File file = new File(reportFileName);
    
    try {
      bw = new BufferedWriter(new FileWriter(file, true));
      bw.write(
          "<TR COLS=5><TD BGCOLOR=#FFE4C4 WIDTH=3%><FONT FACE=VERDANA COLOR=GREEN SIZE=1><center>"
              + sNo + "</center></FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=30%><FONT FACE=VERDANA COLOR=GREEN SIZE=1>" + strTestCaseName
          + "</FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=10%><FONT FACE=VERDANA COLOR=GREEN SIZE=1>" + strStatus
          + "</FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=60%><FONT FACE=VERDANA COLOR=GREEN SIZE=1>" + ""
          + "</FONT></TD>");
      bw.close();
    } catch (IOException e) {

    } finally {
      try {
        if (bw != null)
          bw.close();
      } catch (IOException e) {
      }
    }
  }

  public static void reportFail(String strTestCaseName, String strStatus, String strStackTrace) {
    BufferedWriter bw = null;
    File file = new File(reportFileName);
    try {
      bw = new BufferedWriter(new FileWriter(file, true));
      bw.write(
          "<TR COLS=5><TD BGCOLOR=#FFE4C4 WIDTH=3%><FONT FACE=VERDANA COLOR=RED SIZE=1><center>"
              + sNo + "</center></FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=30%><FONT FACE=VERDANA COLOR=RED SIZE=1>" + strTestCaseName
          + "</FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=10%><FONT FACE=VERDANA COLOR=RED SIZE=1>" + strStatus
          + "</FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=60%><FONT FACE=VERDANA COLOR=RED SIZE=1>" + strStackTrace
          + "</FONT></TD>");
      bw.close();
    } catch (IOException e) {

    } finally {
      try {
        if (bw != null)
          bw.close();
      } catch (IOException e) {
      }
    }
  }
  
  public static void reportIgnored(String strTestCaseName, String strStatus) {
    BufferedWriter bw = null;
    File file = new File(reportFileName);
    String strStackTrace="";
    try {
      bw = new BufferedWriter(new FileWriter(file, true));
      bw.write(
          "<TR COLS=5><TD BGCOLOR=#FFE4C4 WIDTH=3%><FONT FACE=VERDANA COLOR=RED SIZE=1><center>"
              + sNo + "</center></FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=30%><FONT FACE=VERDANA COLOR=BLUE SIZE=1>" + strTestCaseName
          + "</FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=10%><FONT FACE=VERDANA COLOR=BLUE SIZE=1>" + strStatus
          + "</FONT></TD>");
      bw.write("<TD BGCOLOR=#FFE4C4 WIDTH=60%><FONT FACE=VERDANA COLOR=BLUE SIZE=1>" + strStackTrace
          + "</FONT></TD>");
      bw.close();
    } catch (IOException e) {

    } finally {
      try {
        if (bw != null)
          bw.close();
      } catch (IOException e) {
      }
    }
  }

  public static String getCurrentDateTime(String strFormat) {
    Date currentDate = new Date();
    SimpleDateFormat newFormat = new SimpleDateFormat(strFormat);
    return newFormat.format(currentDate);
  }
}


