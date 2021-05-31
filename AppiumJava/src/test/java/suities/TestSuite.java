package suities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.SearchTests;
import tests.ChangeAppConditionTests;
import tests.ArticleTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        SearchTests.class,
        ChangeAppConditionTests.class
})

public class TestSuite {}
