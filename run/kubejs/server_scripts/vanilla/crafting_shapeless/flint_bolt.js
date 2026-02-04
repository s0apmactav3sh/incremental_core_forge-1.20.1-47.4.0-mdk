ServerEvents.recipes(event => {
    event.recipes.minecraft.crafting_shapeless(Item.of('gtceu:flint_bolt', 2), [
        '#gtceu:tools/crafting_saws',
        'gtceu:flint_rod'
    ])
    .damageIngredient('#gtceu:tools/crafting_saws') 
})