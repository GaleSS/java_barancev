package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app)
    {
     this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
        MantisConnectPortType mc = getManticConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator","root");
        return Arrays.asList(projects).stream()
                .map(p -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());

    }

    private MantisConnectPortType getManticConnect() throws ServiceException, MalformedURLException, RemoteException {
        return new MantisConnectLocator().getMantisConnectPort(new URL("https://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws RemoteException, ServiceException, MalformedURLException {
        MantisConnectPortType mc = getManticConnect();
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        String[] categories = mc.mc_project_get_categories("administrator", "root",
                BigInteger.valueOf(issue.getProject().getId()));
        issueData.setCategory(categories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueID = mc.mc_issue_add("administrator","root",issueData);
        IssueData issueCreated = mc.mc_issue_get("administrator","root",issueID);
        return new Issue().withDescription(issueCreated.getDescription()).withSummary(issueCreated.getSummary())
                .withProject(new Project().withId(issueCreated.getProject().getId().intValue())
                                          .withName(issueCreated.getProject().getName()));
    }
}
