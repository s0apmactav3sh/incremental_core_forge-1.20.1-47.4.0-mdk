ServerEvents.recipes(event => {
    event.recipes.minecraft.crafting_shaped(Item.of('gtceu:primitive_workbench', 1),
    [
        'PPP',
        'R R',
        'FFF'
    ], {
      P: '#minecraft:planks',
      R: 'gtceu:stone_rod',
      F: 'gtceu:primitive_construction_floor'
    })
})