ServerEvents.recipes(event => {
    event.recipes.minecraft.crafting_shaped(Item.of('gtceu:primitive_construction_floor', 2),
    [
        'SGS',
        'GSG',
        'SGS'
    ], {
      S: '#gtceu:raw_crafting_stones',
      G: 'gtceu:grout'
    }
    )
})