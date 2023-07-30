import puppeteer from "puppeteer";
import fs from "fs";

async function run() {
  let browser;
  try {
    // we want the browser to not launch when this program runs
    browser = await puppeteer.launch({
      headless: true,
      defaultViewport: null,
    });
    // open a new page
    const page = await browser.newPage();

    await page.goto("https://www.seattlechildrens.org/conditions/a-z/puncture-wound/", {
      waitUntil: "domcontentloaded",
    });

    const scrapeSteps = await page.evaluate(() => {
      // Finding the header that contains "Care Advice for Puncture Wound"
      const careAdviceHeader = Array.from(document.querySelectorAll("h2")).find(
        (header) => header.textContent.trim() === "Care Advice for Puncture Wound"
      );

      if (!careAdviceHeader) {
        throw new Error("Care Advice for Puncture Wound section not found.");
      }

      // Getting the parent element that contains the "Care Advice for Puncture Wound" section
      const careAdviceSection = careAdviceHeader.nextElementSibling;

      // Getting each step from the "Care Advice for Puncture Wound" section
      const steps = careAdviceSection.querySelectorAll("ol li");

      return Array.from(steps).map((step, index) => {
        const stepNum = index + 1;
        const content = step.innerText.trim();

        return { stepNum, content };
      });
    });

    console.log(scrapeSteps);

    await browser.close();

    const jsonData = JSON.stringify(scrapeSteps, null, 2);

    // writing to the local file system
    fs.writeFile("puncture_wound_data.json", jsonData, (error) => {
      if (error) {
        console.error("Error writing to file:", error);
      } else {
        console.log("Successfully saved data to puncture_wound_data.json");
      }
    });
  } catch (error) {
    console.error("Error scraping data:", error);
    if (browser) await browser.close();
  }
}

run();
