package nacorpio.mod; //Package directory

import nacorpio.mod.temperature.ItemManualThermometer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="MoKitchen",name="Mo'Kitchen",version="0.0.0.7")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)

public class MoKitchen {
	
	// Drinks like beer, wine and other drinks.
	public static ItemFood drinkJuiceApple;
	public static ItemFood drinkJuiceRaspberry;
	public static ItemFood drinkJuiceOrange;
	
	public static ItemFood drinkRedWine;
	public static ItemFood drinkWhiteWine;
	public static ItemFood drinkBeer;
	public static ItemFood drinkVodka;
	
	public static Item containerBeer;
	
	
	// Different foods.
	public static ItemFood foodPieApple;
	public static ItemFood foodPieRhubarb;
	public static ItemFood foodDonut;
	public static ItemFood foodOatPorridge;
	public static ItemFood foodWheatPorridge;
	
	
	// Different berries and eat-able things found in the nature.
	// Sandwiches can also be found in here.
	public static ItemFood eatableStrawberry; // TODO: Can be used to make drinks and more.
	public static ItemFood eatableRaspberry; // TODO: Can be used to make drinks and more.
	public static ItemFood eatableOrange; // TODO: This can be used to spice the food up and make drinks.
	public static ItemFood eatableLemon; // TODO: This can be used to spice the food up and make drinks.
	public static ItemFood eatableGrape; // TODO: You are going to be able to create wines out of this.
	
	public static ItemFood eatableSandwich;
	public static ItemFood eatableButteredSandwich; // TODO: The sandwich needs to be 
	public static ItemFood eatableCheeseSandwich;
	public static ItemFood eatableMeatSandwich;
	public static ItemFood eatableMeatAndCheeseSandwich;
	public static ItemFood eatableFishSandwich;
	public static ItemFood eatableSausage;
	
	public static ItemFood eatableFishSalmon, eatableCookedFishSalmon;
	public static ItemFood eatableFishMackerel, eatableCookedFishMackerel;
	public static ItemFood eatableFishHerring, eatableCookedFishHerring;
	
	
	// Different ingredients needed to create the foods.
	public static Item itemMeat;
	public static Item itemCheese; // TODO: Can be used on sandwiches.
	public static Item itemFlour; // TODO: This is an ingredient in a lot of recipes.
	public static Item itemOat; // TODO: Used for porridge.
	public static Item itemSalt; // TODO: Used for most of the meals.
	public static ItemFood itemMeatball;
	
	
	// Accessories (Spices: Can be used on different meals) 
	public static Item accessoryPlate;
	public static Item accessoryWineGlass;
	public static Item accessoryGlass;
	
	public static Item accessoryChiliSpice;
	public static Item accessoryOnionSpice;
	public static Item accessoryBeefSpice;
	
	public static Item accessoryCheeseSlice;
	public static Item accessoryButter; // TODO: This is used on sandwiches.
	
	
	public static ItemManualThermometer otherThermometer;
	
	public static CreativeTabs tabKitchenDrinks;
	public static CreativeTabs tabKitchenContainers;
	public static CreativeTabs tabKitchenMachines;
	public static CreativeTabs tabKitchenFood;
	public static CreativeTabs tabKitchenOther;
	
@Init
	public void load(FMLInitializationEvent event){
		
		itemAdding();
		languageRegistering();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabKitchenDrinks", "en_US", "Mo' Kitchen Drinks");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabKitchenContainers", "en_US", "Mo' Kitchen Containers");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabKitchenMachines", "en_US", "Mo' Kitchen Machines");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabKitchenFood", "en_US", "Mo' Kitchen Food");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabKitchenOther", "en_US", "Mo' Kitchen Other");
		
	}

	private void itemAdding(){
		
		tabKitchenDrinks = new CreativeTabKitchen("tabKitchenDrinks");
		tabKitchenContainers = new CreativeTabs("tabKitchenContainers"){
			public ItemStack getIconItemStack() {
                return new ItemStack(MoKitchen.containerBeer, 1, 0);
			}
		};
		tabKitchenMachines = new CreativeTabs("tabKitchenMachines"){
			public ItemStack getIconItemStack() {
                return new ItemStack(Block.brick, 1, 0);
			}
		};
		tabKitchenFood = new CreativeTabs("tabKitchenFood"){
			public ItemStack getIconItemStack() {
                return new ItemStack(MoKitchen.itemMeatball, 1, 0);
			}
		};
		tabKitchenOther = new CreativeTabs("tabKitchenOther"){
			public ItemStack getIconItemStack() {
                return new ItemStack(MoKitchen.otherThermometer, 1, 0);
			}
		};
		
		eatableSausage = new ItemFood(3363, 6, false) {};
		eatableSausage.setUnlocalizedName("sausage");
		eatableSausage.setCreativeTab(this.tabKitchenFood);
		
		drinkJuiceApple = new ItemFood(3359, 4, false) {};
		drinkJuiceApple.setUnlocalizedName("apple_juice");
		drinkJuiceApple.setCreativeTab(this.tabKitchenDrinks);
		
		drinkJuiceRaspberry = new ItemFood(3360, 4, false) {};
		drinkJuiceRaspberry.setUnlocalizedName("raspberry_juice");
		drinkJuiceRaspberry.setCreativeTab(this.tabKitchenDrinks);
		
		drinkJuiceOrange = new ItemFood(3361, 4, false) {};
		drinkJuiceOrange.setUnlocalizedName("orange_juice");
		drinkJuiceOrange.setCreativeTab(this.tabKitchenDrinks);
		
		itemMeatball = new ItemFood(3362, 2, false) {};
		itemMeatball.setUnlocalizedName("meetball");
		itemMeatball.setCreativeTab(this.tabKitchenFood);
		
//		eatableStrawberry = (ItemFood) new ItemFood(3349, 1, false).setUnlocalizedName("strawberry");
//		eatableRaspberry = (ItemFood) new ItemFood(3354, 1, false).setUnlocalizedName("raspberry");
//		eatableOrange = (ItemFood) new ItemFood(3355, 1, false).setUnlocalizedName("orange");
//		eatableLemon = (ItemFood) new ItemFood(3356, 1, false).setUnlocalizedName("lemon");
//		eatableGrape = (ItemFood) new ItemFood(3357, 1, false).setUnlocalizedName("grape");

//		foodDonut = (ItemFood) new ItemFood(3337, 3, false).setUnlocalizedName("donut").setCreativeTab(CreativeTabs.tabKitchen);
//		foodPieApple = (ItemFood) new ItemFood(3345, 12, false).setUnlocalizedName("apple_pie").setCreativeTab(CreativeTabs.tabKitchen);
//		foodPieRhubarb = (ItemFood) new ItemFood(3346, 12, false).setUnlocalizedName("rhubarb_pie").setCreativeTab(CreativeTabs.tabKitchen);
//		foodOatPorridge = (ItemFood) new ItemFood(3347, 6, false).setUnlocalizedName("oat_porridge").setCreativeTab(CreativeTabs.tabKitchen);
//		foodWheatPorridge = (ItemFood) new ItemFood(3348, 6, false).setUnlocalizedName("wheat_porridge").setCreativeTab(CreativeTabs.tabKitchen);

//		itemCheese = new Item(3338).setUnlocalizedName("cheese_full").setCreativeTab(CreativeTabs.tabKitchen);
//		itemMeat = new Item(3341).setUnlocalizedName("meat").setCreativeTab(CreativeTabs.tabKitchen);
//		itemFlour = new Item(3342).setUnlocalizedName("flour").setCreativeTab(CreativeTabs.tabKitchen);
//		itemOat = new Item(3343).setUnlocalizedName("oat").setCreativeTab(CreativeTabs.tabKitchen);
//		itemSalt = new Item(3344).setUnlocalizedName("salt").setCreativeTab(CreativeTabs.tabKitchen);

//		accessoryChiliSpice = new Item(3349).setUnlocalizedName("chili_spice").setCreativeTab(CreativeTabs.tabKitchen);
//		accessoryOnionSpice = new Item(3350).setUnlocalizedName("onion_spice").setCreativeTab(CreativeTabs.tabKitchen);
//		accessoryBeefSpice = new Item(3351).setUnlocalizedName("beef_spice").setCreativeTab(CreativeTabs.tabKitchen);
//		accessoryCheeseSlice = new Item(3352).setUnlocalizedName("cheese_slice").setCreativeTab(CreativeTabs.tabKitchen);
//		accessoryButter = new Item(3353).setUnlocalizedName("butter").setCreativeTab(CreativeTabs.tabKitchen);
		
		containerBeer = new Item(3358).setUnlocalizedName("container_beer").setMaxStackSize(1).setCreativeTab(this.tabKitchenContainers);
		
		drinkRedWine = (ItemFood) new ItemFood(3339, 1, false).setUnlocalizedName("red_wine").setMaxStackSize(1).setCreativeTab(this.tabKitchenDrinks);
		drinkWhiteWine = (ItemFood) new ItemFood(3340, 1, false).setUnlocalizedName("white_wine").setMaxStackSize(1).setCreativeTab(this.tabKitchenDrinks);
		drinkBeer = new ItemFood(3341, 3, false){
			@Override
			protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
				int second = 1000 / 4 / 11;
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, second * 10, 0));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, second * 5, 0));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, second * 4, 0));
			}
		};
		drinkBeer.setMaxStackSize(1);
		drinkBeer.setUnlocalizedName("beer").setCreativeTab(this.tabKitchenDrinks);
		drinkBeer.setPotionEffect(Potion.moveSlowdown.id, 5, 0, 1.0F);
		drinkBeer.setPotionEffect(Potion.regeneration.id, 10, 0, 1.0F);
		// drinkBeer.setPotionEffect(9, 4, 0, 1.0F);

		otherThermometer = new ItemManualThermometer(){};
		otherThermometer.setUnlocalizedName("thermometer");
		otherThermometer.setCreativeTab(this.tabKitchenOther);
		
		// TODO: Cooking the milk so that cheese is created.
		//GameRegistry.addSmelting(3338, new ItemStack(Item.bucketMilk), 0.1f);
		
		// TODO: Create a sandwich.
		//GameRegistry.addRecipe(new ItemStack(eatableSandwich), "xxx", "yyy", "zzz",
		//'x', new ItemStack(accessoryButter), 'y', new ItemStack(itemFlour), 'z', new ItemStack(Item.wheat));
		
		// TODO: Create a buttered sandwich.
		//GameRegistry.addShapelessRecipe(new ItemStack(eatableButteredSandwich), new ItemStack(eatableSandwich), new ItemStack(accessoryButter));
		
		// TODO: Create a sandwich with cheese on it.
		//GameRegistry.addShapelessRecipe(new ItemStack(eatableCheeseSandwich), new ItemStack(eatableButteredSandwich), new ItemStack(accessoryCheeseSlice));
		
		// TODO: Create a sandwich with cheese and meat on it.
		//GameRegistry.addShapelessRecipe(new ItemStack(eatableMeatAndCheeseSandwich), new ItemStack(eatableCheeseSandwich), new ItemStack(itemMeat));
		
		// TODO: Create a sandwich with meat on it.
		//GameRegistry.addShapelessRecipe(new ItemStack(eatableMeatSandwich), new ItemStack(eatableButteredSandwich), new ItemStack(itemMeat));

	}

	private void languageRegistering(){
		
		// LanguageRegistry.addName(eatableStrawberry, "Strawberry");
		
		// LanguageRegistry.addName(foodDonut, "Donut");
		// LanguageRegistry.addName(foodPieApple, "Apple Pie");
		// LanguageRegistry.addName(foodPieRhubarb, "Rhubarb Pie");
		// LanguageRegistry.addName(foodOatPorridge, "Oat Porridge");
		// LanguageRegistry.addName(foodWheatPorridge, "Wheat Porridge");
		
		// LanguageRegistry.addName(itemCheese, "Cheese");
		// LanguageRegistry.addName(itemMeat, "Meat");
		// LanguageRegistry.addName(itemFlour, "Flour");
		// LanguageRegistry.addName(itemOat, "Oat");
		// LanguageRegistry.addName(itemSalt, "Salt");

		// LanguageRegistry.addName(accessoryChiliSpice, "Chili Spice");
		// LanguageRegistry.addName(accessoryOnionSpice, "Onion Spice");
		// LanguageRegistry.addName(accessoryBeefSpice, "Beef Spice");
		// LanguageRegistry.addName(accessoryCheeseSlice, "Cheese Slice");
		// LanguageRegistry.addName(accessoryButter, "Butter");
		
		LanguageRegistry.addName(drinkRedWine, "Red Wine");
		LanguageRegistry.addName(drinkWhiteWine, "White Wine");
		LanguageRegistry.addName(drinkBeer, "Beer");
		LanguageRegistry.addName(containerBeer, "Beer Container");
		LanguageRegistry.addName(drinkJuiceApple, "Apple Juice");
		LanguageRegistry.addName(drinkJuiceRaspberry, "Raspberry Juice");
		LanguageRegistry.addName(drinkJuiceOrange, "Orange Juice");
		LanguageRegistry.addName(itemMeatball, "Meetball");
		LanguageRegistry.addName(eatableSausage, "Sausage");
		LanguageRegistry.addName(otherThermometer, "Thermometer");
		
	}

}