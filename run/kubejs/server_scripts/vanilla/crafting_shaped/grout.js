ServerEvents.recipes(event => {
    event.recipes.minecraft.crafting_shaped(Item.of('gtceu:grout', 3),
    [
        'SSS',
        'SCG',
        'GGG'
    ], {
      S: 'minecraft:sand',
      C: 'minecraft:clay',
      G: 'minecraft:gravel'
    }
    )
})