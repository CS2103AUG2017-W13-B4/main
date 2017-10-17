package guitests;

import org.junit.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.module.Lesson;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.TestUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static seedu.address.ui.testutil.GuiTestAssert.assertListMatching;

public class SampleDataTest extends AddressBookGuiTest {
    @Override
    protected AddressBook getInitialData() {
        // return null to force test app to load data from file only
        return null;
    }

    @Override
    protected String getDataFileLocation() {
        // return a non-existent file location to force test app to load sample data
        String filePath = TestUtil.getFilePathInSandboxFolder("SomeFileThatDoesNotExist1234567890.xml");
        deleteFileIfExists(filePath);
        return filePath;
    }

    /**
     * Deletes the file at {@code filePath} if it exists.
     */
    private void deleteFileIfExists(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException ioe) {
            throw new AssertionError(ioe);
        }
    }

    @Test
    public void addressBook_dataFileDoesNotExist_loadSampleData() {
        Lesson[] expectedList = SampleDataUtil.getSampleLessons();
        assertListMatching(getPersonListPanel(), expectedList);
    }
}
