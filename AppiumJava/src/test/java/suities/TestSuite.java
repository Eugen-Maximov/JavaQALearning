package suities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.MyListsTests;
import tests.SearchTests;
import tests.ChangeAppConditionTests;
import tests.ArticleTests;
import tests.iOS.GetStartedTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        SearchTests.class,
        ChangeAppConditionTests.class,
        MyListsTests.class,
        GetStartedTest.class
})

public class TestSuite {}
