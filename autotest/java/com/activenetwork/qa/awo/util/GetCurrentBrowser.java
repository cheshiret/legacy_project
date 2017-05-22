package com.activenetwork.qa.awo.util;

import com.thoughtworks.selenium.*;
import org.openqa.selenium.*;


//public class GetCurrentBrowser : RemoteWebDriver {
//
//	    public static bool newSession;
//	    public static string capPath = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "TestFiles", "tmp", "sessionCap");
//	    public static string sessiodIdPath = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "TestFiles", "tmp", "sessionid");
//
//	    public CustomRemoteWebDriver(Uri remoteAddress) 
//	        : base(remoteAddress, new DesiredCapabilities())
//	    {
//	    }
//
//	    protected override Response Execute(DriverCommand driverCommandToExecute, Dictionary<string, object> parameters)
//	    {
//	        if (driverCommandToExecute == DriverCommand.NewSession)
//	        {
//	            if (!newSession)
//	            {
//	                var capText = File.ReadAllText(capPath);
//	                var sidText = File.ReadAllText(sessiodIdPath);
//
//	                var cap = JsonConvert.DeserializeObject<Dictionary<string, object>>(capText);
//	                return new Response
//	                {
//	                    SessionId = sidText,
//	                    Value = cap
//	                };
//	            }
//	            else
//	            {
//	                var response = base.Execute(driverCommandToExecute, parameters);
//	                var dictionary = (Dictionary<string, object>) response.Value;
//	                File.WriteAllText(capPath, JsonConvert.SerializeObject(dictionary));
//	                File.WriteAllText(sessiodIdPath, response.SessionId);
//	                return response;
//	            }
//	        }
//	        else
//	        {
//	            var response = base.Execute(driverCommandToExecute, parameters);
//	            return response;
//	        }
//	    }
//	}

