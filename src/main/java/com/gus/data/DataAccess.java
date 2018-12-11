package com.gus.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public  class DataAccess {

    public List<String> getFileNamesInResources() throws IOException {
        List<String> jsonFileNames = new ArrayList<>();
        List jsonFilesInFolder =
                Files.walk(Paths.get("C:\\Users\\wernerowiczm\\IdeaProjects\\jsonToCBOPdb\\src\\main\\resources\\cbop"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        for (int i = 0; i < jsonFilesInFolder.size(); i++) {
            String path = jsonFilesInFolder.get(i).toString();
            String simplePath = path.substring(path.length() - 14);
            jsonFileNames.add(simplePath);
        }
        return jsonFileNames;
    }
}
