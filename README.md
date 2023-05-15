# Stardew mine
## Game Overview:
Stardew Mine is a grid-based mining game in which players gather various types of gems by breaking rocks. 
We plan to introduce features similar to Minecraft, allowing users to craft unique weapons using the collected gems.
- COMSC-341GP Game Programming Class Team Project: [Presentation Slides](https://docs.google.com/presentation/d/1t5ZGmrFqCwlhLDc3hhbzrQMC3Y0gWtffb1AkS2b0iLs/edit?usp=sharing)

<img width="400" alt="Screenshot 2023-05-08 at 3 09 08 PM" src="https://user-images.githubusercontent.com/89917595/236911407-7472b156-f41c-486d-90bd-4c4642a82f60.png"> <img width="400" alt="Screenshot 2023-05-08 at 3 09 11 PM" src="https://user-images.githubusercontent.com/89917595/236911437-bcd3670c-1b72-43ea-b0fb-f22b97646913.png">
<img width="400" alt="Screenshot 2023-05-08 at 3 09 15 PM" src="https://user-images.githubusercontent.com/89917595/236911445-812842c4-a92a-40de-b2d5-8d207f569e39.png"> <img width="400" alt="Screenshot 2023-05-08 at 3 09 22 PM" src="https://user-images.githubusercontent.com/89917595/236911458-c584c3ef-0ec2-4dfe-a743-5d1b36770aae.png">
<img width="400" alt="Screenshot 2023-05-08 at 3 09 36 PM" src="https://user-images.githubusercontent.com/89917595/236911465-cf180ac5-3845-4b95-8b0c-44e77452b9b0.png"> <img width="400" alt="Screenshot 2023-05-08 at 3 09 47 PM" src="https://user-images.githubusercontent.com/89917595/236911473-e1dbaf6e-4f41-4d0d-b62d-da46c05a6f46.png">



## Demo

https://user-images.githubusercontent.com/89917595/236913570-a78abd2c-de51-4ced-9927-ac77ec12f0f4.mov

https://user-images.githubusercontent.com/89917595/236913590-606b3ceb-2a79-4c39-8f22-c0cd1f2a1ad5.mov

https://user-images.githubusercontent.com/89917595/236913436-58724b0f-54d2-45df-80f0-fea192d5ce09.mov

https://user-images.githubusercontent.com/89917595/236913602-b45c5f58-e938-442d-972d-63d21e2ffd5e.mov


## Parts that I implemented 

### Ver 2:
   ### Crafting
   <img width="400" alt="Screenshot 2023-05-06 at 11 45 49 PM" src="https://user-images.githubusercontent.com/89917595/236914477-80518199-4ed4-41ba-bcd3-16bb5c38c7e4.png"> <img width="400" alt="Screenshot 2023-05-06 at 11 45 53 PM" src="https://user-images.githubusercontent.com/89917595/236914496-6bc4ea52-46c7-487b-bb00-a4f2a44c887a.png">

   <img width="400" alt="Screenshot 2023-05-06 at 11 46 05 PM" src="https://user-images.githubusercontent.com/89917595/236914511-d2e3a137-82ab-4ab8-beaf-1bbe8be17237.png"> <img width="400" alt="Screenshot 2023-05-06 at 11 46 21 PM" src="https://user-images.githubusercontent.com/89917595/236914526-bbc95f35-af71-4704-b86c-b0c4b5cc75ca.png">

   - User can click on the SmithingTable
   - Crafting is unavailable without enough gems
   - User can exchange 2 diamonds and 3 stones to a diamond sword
      - Sword will be added to inventory and change character image

   #### Crafting Implementation 
   1. Add SmithingTable (6) to the map file
   2. Incorporate graphics (smithing table, crafting backgrounds/buttons, character with sword)
   3. Create SmithingTable class extending Feature class
   4. Create CraftingMenu class with craftDiamondSword function
   5. Update Player class to handle sword possession
   6. Display appropriate CraftingMenu screen based on user's resources
   7. Show craft button only if resources sufficient
   (For the future improvement, adding an equipping/unequipping method in the Player class would increase flexibility for other weapons!)
<hr/>

### Ver 1:
   ### Inventory
   <img width="600" alt="Screenshot 2023-05-04 at 5 43 17 PM" src="https://user-images.githubusercontent.com/89917595/236336544-13c04efe-2657-4acc-8abd-8abf6a76ab14.png">

   ### Map Generation
   <img width="600" alt="Screenshot 2023-05-04 at 5 43 25 PM" src="https://user-images.githubusercontent.com/89917595/236336543-6cfae336-280d-4d04-8927-5705ac58f291.png">

   ### Character Animation
   <img width="600" alt="Screenshot 2023-05-04 at 5 43 51 PM" src="https://user-images.githubusercontent.com/89917595/236336540-bae86676-9e01-4791-a537-da14d90dd495.png">

   https://user-images.githubusercontent.com/89917595/236336841-d902d833-6ff3-4b4a-938c-46250543c67d.mov



## Game Design
- Grid-based map
- Generating tiles and rocks from a text file
- Animated character movement
- Rock destruction > Item drops > Add to inventory
- Collision detection
   - Background & character
   - Dropped items & character
- Map Scrolling
- Inventory management
- Sound effect

## Input
- Use WASD or arrow keys for character movement
- Press spacebar or click the rock to break an adjacent rock
