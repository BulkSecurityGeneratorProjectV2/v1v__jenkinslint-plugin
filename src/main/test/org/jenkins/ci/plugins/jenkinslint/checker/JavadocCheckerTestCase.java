package org.jenkins.ci.plugins.jenkinslint.checker;

import hudson.model.FreeStyleProject;
import hudson.tasks.ArtifactArchiver;
import hudson.tasks.JavadocArchiver;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JavadocChecker Test Case.
 *
 * @author Victor Martinez
 */
public class JavadocCheckerTestCase {
    private JavadocChecker checker = new JavadocChecker("JavadocChecker", false, false);

    @Rule public JenkinsRule j = new JenkinsRule();
    @Test public void testEmptyJob() throws Exception {
        FreeStyleProject project = j.createFreeStyleProject();
        assertFalse(checker.executeCheck(project));
    }
    @Test public void testPublisherJob() throws Exception {
        FreeStyleProject project = j.createFreeStyleProject();
        project.getPublishersList().add(new ArtifactArchiver("","",false));
        assertFalse(checker.executeCheck(project));
    }
    @Test public void testEmptyJavadocPublisher() throws Exception {
        FreeStyleProject project = j.createFreeStyleProject();
        project.getPublishersList().add(new JavadocArchiver("",false));
        assertTrue(checker.executeCheck(project));
    }
    @Test public void testArtifactPublisher() throws Exception {
        FreeStyleProject project = j.createFreeStyleProject();
        project.getPublishersList().add(new JavadocArchiver("something",false));
        assertFalse(checker.executeCheck(project));
    }
}