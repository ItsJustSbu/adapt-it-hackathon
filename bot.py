import re
import json

# Read and preprocess your custom JSON data
with open("path/to/your/custom_data.json", "r") as json_file:
    data = json.load(json_file)

# Extract the first aid steps
first_aid_steps = {entry["stepNum"]: entry["content"] for entry in data}

# Define regular expressions for matching user input
cpr_request_pattern = r"(help|instruction|steps).*CPR"
breathing_pattern = r"\bbreath(e|ing)\b"
emergency_indicators = [
    r"(unresponsive|not responding)",
    r"(not breathing|stopped breathing|no breath|can't breathe)",
    r"(no pulse|no heartbeat)",
    r"(choking|gagging|can't breathe|airway blocked)",
    r"(chest pain|pain in the chest|shortness of breath|radiating pain in arm|pain in the left arm)",
    r"(drowning|underwater|submerged)",
    r"(severe injury|serious accident|fall from height|high impact injury|unconscious after injury)",
    r"(suddenly collapsed|fell down suddenly|loss of consciousness)"
]

# Start the conversation with the bot
print("Bot: Hi! I'm here to help you with first aid. You can ask me for step-by-step instructions by providing the step number or ask about CPR in general. Type 'exit' to end the conversation.")

while True:
    user_input = input("You: ").lower()
    
    # Check if the user wants to exit the conversation
    if user_input == "exit":
        print("Bot: Goodbye! Stay safe.")
        break

    # Check if the user asks about CPR in general or mentions breathing
    if re.search(cpr_request_pattern, user_input) or re.search(breathing_pattern, user_input):
        # List all CPR steps
        cpr_steps = "\n".join([f"Step {step}: {first_aid_steps[step]}" for step in sorted(first_aid_steps.keys())])
        response = f"Sure! Here are the steps for CPR:\n{cpr_steps}"
        print("Bot:", response)
    else:
        # Check for other emergency indicators
        for indicator_pattern in emergency_indicators:
            if re.search(indicator_pattern, user_input):
                print("Bot: I'm sorry to hear that you might be experiencing a medical emergency. Please call emergency services (e.g., 911) immediately for assistance.")
                break
        else:
            print("Bot: I'm sorry, I don't have instructions for that topic. Please ask about CPR or mention a medical emergency for assistance.")
