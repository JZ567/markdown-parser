import static org.junit.Assert.*;
import org.junit.*;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;


public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);

    }

    public void addition2() {
        assertEquals(2, 1 + 1);

    }

/**
    @Test
    public void test() throws IOException{
        assertEquals(2, 1+1);
        
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(links, List.of("https://something.com", "some-thing.html"));
        
        //(List.of("https://something.com", "some-thing.html"), MarkdownParse.getLinks(Files.readString(Path.of("test-file.md"))));
    }

    @Test
    public void failTest() {
        assertEquals(2, 1+1);

    }

    @Test
    public void testFile8() throws IOException{
        Path fileName = Path.of("test-file8.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(links, List.of("a link on the first line"));

    }

*/
    @Test
    public void testSnippet1() throws IOException{
        Path fileName = Path.of("snippet-1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(links, List.of("google.com", "google.com", "ucsd.com"));

    }

    @Test
    public void testSnippet2() throws IOException{
        Path fileName = Path.of("snippet-2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(links, List.of("a.com", "a.com(())", "example.com"));

    }

    @Test
    public void testSnippet3() throws IOException{
        Path fileName = Path.of("snippet-3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(links, List.of("https://www.twitter.com", 
        "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule",
        "https://cse.ucsd.edu/"));

    }

}

