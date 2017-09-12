package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProblemPage {


     private static WebElement element;
                
     public static WebElement getAssignmentGrpEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem.assignment_group"));
        return element;
       }
     public static WebElement getSourceDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.contact_type"));
        return element;
       }
     public static WebElement getImpactDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.impact"));
        return element;
       }
     public static WebElement getComplexityDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.urgency"));
        return element;
       }
     public static WebElement getConfigurationitemEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem.cmdb_ci"));
        return element;
       }
     public static WebElement getShortdescriptionEdt(WebDriver driver) {
        element = driver.findElement(By.id("problem.short_description"));
        return element;
       }
     public static WebElement getDescriptionEdt(WebDriver driver) {
        element = driver.findElement(By.id("problem.description"));
        return element;
       }
  
     public static WebElement getSubmitBtn(WebDriver driver) {
        element = driver.findElement(By.id("sysverb_insert"));
        return element;
       }
    public static WebElement getMajorProblemChkbox(WebDriver driver) {
    	element = driver.findElement(By.id("label.ni.problem.u_major_problem"));
    	return element;
       }
    
     public static WebElement getSearchDropDown(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//div[@class='input-group']//select"));
    	 return element;
       }
     
     public static WebElement getSearchProblemEdt(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
    	 return element;
       }
     
     public static WebElement getProblemStatusfromQueue(WebDriver driver, String problemId) throws Exception{
 		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+problemId+"']/following::td[2]"));
 		return element;
 	   }
     
     public static WebElement getServiceForumTab(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//span[contains(text(),'Service') and contains(text(),'Forum')]"));
    	 return element;
       }
     
     public static WebElement getDivisionDropDown(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_division"));
    	 return element;
       }
       
     public static WebElement getForumDropDown(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_forum"));
    	 return element;
       }
       
     public static WebElement getSeverityDropDown(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_severity"));
    	 return element;
       }
       
     public static WebElement getSFUpdateEdt(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_sf_update"));
    	 return element;
       }
     
     public static WebElement getImpactAndAnalysisTab(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//span[contains(text(),'Impact') and contains(text(),'Analysis')]"));
    	 return element;
       }
     
     public static WebElement getNumberOfCustomerCallsEdt(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_number_of_customer_calls"));
    	 return element;
       }
     
     public static WebElement getIncidentStartTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_start_time"));
         return element;
       }
     
       public static WebElement getIncidentDetectedTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_detected_time"));
         return element;
       }
       
       public static WebElement getIncidentMitigationTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_mitigation_time"));
         return element;
       }
       
       public static WebElement getIncidentResolvedTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_resolved_time"));
         return element;
       }
       
       public static WebElement getImpactedServicesTab(WebDriver driver) throws Exception {
         element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Impacted') and contains(text(),'Services')]"));
         return element;
       }
       
       public static WebElement getProblemTasksTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Problem') and contains(text(),'Tasks')]"));
    	   return element;
       }
       
       public static WebElement getIncidentsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Incidents')]"));
    	   return element;
       }
       
       public static WebElement getChildProblemsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Child') and contains(text(),'Problems')]"));
    	   return element;
       }
       
       public static WebElement getKnowledgeTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Knowledge')]"));
    	   return element;
       }
       
       public static WebElement getApproversTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Approvers')]"));
    	   return element;
       }
       
       public static WebElement getMetricsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Metrics')]"));
    	   return element;
       }
       
       public static WebElement getServiceForumsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Service') and contains(text(),'Forums')]"));
    	   return element;
       }
       
       //Problem Approvers is pending
       public static WebElement getProblemTaskNewBtn(WebDriver driver) throws Exception {
    	   element = driver.findElement(By.id("sysverb_new"));
    	   return element;
       }
               
       //In Progress
       
       public static WebElement getRootCauseAnalysisCompleteChkbox(WebDriver driver) {
    	   element = driver.findElement(By.id("label.ni.problem.u_root_cause_analysis_complete"));
    	   return element;
       }
       
       public static WebElement getWorkaroundEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_work_around_str"));
    	   return element;
       }
       
       public static WebElement getTriggerCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_trigger_category"));
    	   return element;
       }
       
       public static WebElement getTriggerDetailsEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_trigger_details"));
    	   return element;
       }
       
       public static WebElement getRootCauseCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_root_cause_category"));
    	   return element;
       }
       
       public static WebElement getRootCauseSubCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_root_cause_subcategory"));
    	   return element;
       }
       
       public static WebElement getRootCauseDetailsEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_root_cause"));
    	   return element;
       }
       
       public static WebElement getRootCauseCIEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("sys_display.problem.u_root_cause_ci"));
    	   return element;
       }
       
       public static WebElement getAvoidanceFailureCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_avoidance_failure_category"));
    	   return element;
       }
       
       public static WebElement getAvoidanceFailureDetailsEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_avoidance_failure_details"));
    	   return element;
       }
       
       public static WebElement getResolutionEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_resolution"));
    	   return element;
       }
       
        //Known Error
       public static WebElement getNotesTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//span[contains(text(),'Notes') and contains(text(),'Notes')]"));
    	   return element;
       }
       
       public static WebElement getStakeholderListUnlockBtn(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.watch_list_unlock"));
    	   return element;
       }
       
       public static WebElement getStakeholderListEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("sys_display.problem.watch_list"));
    	   return element;
       }
       
       public static WebElement getStakeholderListLockBtn(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.watch_list_lock"));
    	   return element;
       }
       
       public static WebElement getProblemNumberFromQueue(WebDriver driver, String problemNumber){
       	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+problemNumber+"']"));
       	return element;
       }

       public static WebElement getProblemApproversTab(WebDriver driver){
       	element = driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Approvers')]"));
       	return element;
       }
       
       public static WebElement getProblemPendingApproverLnk(WebDriver driver,String usrName){
          	element = driver.findElement(By.xpath("//table[@id='problem.sysapproval_approver.sysapproval_table']//a[text()='"+usrName+"']//parent::td//preceding-sibling::td[3]"));
          	return element;
          }
        
       public static WebElement getProblemApproveBtn(WebDriver driver){
       	element = driver.findElement(By.id("approve"));
       	return element;
       }
       
       public static WebElement getProblemStateEdtDropDown(WebDriver driver){
       	element = driver.findElement(By.id("problem.state"));
       	return element;
       }
}
