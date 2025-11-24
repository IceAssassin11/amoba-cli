package hu.egyetem.amoba;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void mainRunsWithoutException() {
        try {
            String[] args = {};
            App.main(args);
        } catch (Exception e) {
            fail("Az Amőba játék futás közben hibába botlott: " + e.getMessage());
        }
    }
}