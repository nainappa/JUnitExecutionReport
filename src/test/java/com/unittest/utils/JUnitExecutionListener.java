package com.unittest.utils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public class JUnitExecutionListener extends RunListener {
  public static boolean blnFailed=false;
  
  @Override
  public void testRunStarted(Description description) throws Exception {
    HTMLReport.createHTMLTemplate("Your own env",description.getClassName().split("\\.")[description.getClassName().split("\\.").length-1]);
    System.out.println("Number of tests to execute: " + description.getMethodName());
  }

  @Override
  public void testRunFinished(Result result) throws Exception {
    System.out.println(result.getFailureCount());
    System.out.println("Number of tests executed: " + result.getRunCount());
  }

  @Override
  public void testStarted(Description description) throws Exception {
    HTMLReport.sNo=HTMLReport.sNo+1;
    blnFailed=false;
    System.out.println("Starting: " + description.getMethodName());
  }

  @Override
  public void testFinished(Description description) throws Exception {
    if(!blnFailed){
      HTMLReport.reportPass(description.getMethodName(), "Passed");
    }
    System.out.println("Finished: " + description.getMethodName());
  }

  @Override
  public void testFailure(Failure failure) throws Exception {
    blnFailed=true;
    HTMLReport.reportFail(failure.getDescription().getMethodName(), "failed",
     failure.getTrace());
    System.out.println("Failed: " + failure.getDescription().getMethodName());
  }

  @Override
  public void testAssumptionFailure(Failure failure) {
    System.out.println("Failed: " + failure.getDescription().getMethodName());
  }

  @Override
  public void testIgnored(Description description) throws Exception {
    HTMLReport.sNo=HTMLReport.sNo+1;
    HTMLReport.reportIgnored(description.getMethodName(), "Skipped");
    System.out.println("Ignored: " + description.getMethodName());
  }

}
