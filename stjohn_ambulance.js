import puppeteer from "puppeteer";
import fs from "fs";


async function run(){
    let browser;

    //we want the brower to not launch when this programme runs
    browser = await puppeteer.launch({
        headless : true,
        defaultViewport: null,
    });
    //open a new page
    const page = await browser.newPage();

    await page.goto("https://www.sja.org.uk/get-advice/first-aid-advice/choking/adult-choking/", {
        waitUntil: "domcontentloaded",
      });

    const scrapeSteps = await page.evaluate(()=>{

        var i = 0;

        //getting each step from website 
        const steps = document.querySelectorAll(".numberedpod__body");

        return Array.from(steps).map((step)=>{
            const stepNum = i+1;
        const content = step.innerText;

        return {stepNum,content}
        });
        
    });

    console.log(scrapeSteps);

    await browser.close();

    const jsonData = JSON.stringify(scrapeSteps, null, 2);
    
    //writing to local file system
    fs.writeFile("chocking_data.json", jsonData, (error)=>{
        if (error){
            console.error("error writing to file:", error);
        }else{
            console.log("successfull");
        }
    });
}

run();