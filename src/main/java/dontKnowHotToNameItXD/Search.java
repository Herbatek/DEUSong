package dontKnowHotToNameItXD;

import converters.FileToSongConverter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private Filter[] filters = {
            new Filter("zwykle", true), //0
            new Filter("wielki post", true), //1
            new Filter("wielkanoc", true), //2
            new Filter("adwent", true), //3
            new Filter("koledy", true), //4
            new Filter("", true), //5
            new Filter("swiety", true), //6
    };

    private List<Song> songs = new ArrayList<>();

    public List<Song> findByTitle(String text) {
        songs.clear();

        for (Filter filter : filters) {
            if (filter.getState())
                search(text, filter.getPath(), filter.getCategory());
        }
        return songs;
    }

    private void search(String text, String directory, String category) {
        File dir = new File(directory);

        File[] files = dir.listFiles((dir1, name) -> StringUtils.containsIgnoreCase(name, text)
                && (name.endsWith(".pptx")));

        if (files != null)
            for (File file : files)
                songs.add(FileToSongConverter.toSong(file, category));
    }

    public Filter[] getFilters() {
        return filters;
    }

    public void setAllFiltersFalse(Filter[] filters) {
        for (Filter filter : filters)
            filter.setState(false);
    }

    public void setAllFiltersTrue(Filter[] filters) {
        for (Filter filter : filters)
            filter.setState(true);
    }
}
