import puppeteer from "puppeteer";
import fs from "fs";
import { error } from "console";


async function run(){
    let browser;

    //we want the brower to not launch when this programme runs
    browser = await puppeteer.launch({
        headless : true,
        defaultViewport: null,
    });
    //open a new page
    const page = await browser.newPage();

    await page.goto("https://www.redcross.org/take-a-class/cpr/performing-cpr/cpr-steps", {
        waitUntil: "domcontentloaded",
      });

    const scrapeSteps = await page.evaluate(()=>{

        //getting each step from website 
        const steps = document.querySelectorAll(".template5-bullet-red-container");

        return Array.from(steps).map((step)=>{
            const stepNum = step.querySelector(".bullet-red").innerText;
        const content = step.querySelector(".template5-bullet-content").innerText;

        return {stepNum,content}
        });
        
    });

    console.log(scrapeSteps);

    await browser.close();

    const jsonData = JSON.stringify(scrapeSteps, null, 2);
    
    //writing to local file system
    fs.writeFile("scraped_data.json", jsonData, (error)=>{
        if (error){
            console.error("error writing to file:", error);
        }else{
            console.log("successfull");
        }
    });
}

run();