package com.activenetwork.qa.awo.testcases;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;


//The code works
public class TestSikuli {
    public static void main(String[] args) {
        Screen s = new Screen();
        try{
                s.click("imgs/StartIcon.png", 0);
                s.wait("imgs/RunInput.png");
                s.type(null, "hello world\n", 0);
        }
        catch(FindFailed e){
                e.printStackTrace();
        }
}
}