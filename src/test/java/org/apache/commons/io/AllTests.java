package org.apache.commons.io;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({FileUtilsCopyTest.class, FileUtilsCopy2Test.class})
public class AllTests {
}
