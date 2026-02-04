ServerEvents.recipes(event => {
    event.recipes.minecraft.crafting_shapeless(Item.of('gtceu:flint_rod', 1), [
        '#gtceu:tools/crafting_files',
        'minecraft:flint'
    ])
    .damageIngredient('#gtceu:tools/crafting_files')
})