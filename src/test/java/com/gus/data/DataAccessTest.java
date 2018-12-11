package com.gus.data;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DataAccessTest {
    DataAccess dataAccess = new DataAccess();

    @Test
    public void shouldGetTheSameFileNames() throws IOException {
        List nameFiles = dataAccess.getFileNamesInResources();
        Assert.assertEquals("02_000001.json", nameFiles.get(0));
        Assert.assertEquals("32_000002.json", nameFiles.get(nameFiles.size()-1));
    }
}