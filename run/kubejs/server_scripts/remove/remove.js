let yeet = (itemName) => {
  ServerEvents.recipes(event => {
    event.remove({ output: itemName })
  })
  ServerEvents.tags('item', event => {
    console.log('[21] - [1] - TAG-WATCHER')
    event.add('forge:viewers/hidden_from_recipe', itemName)
    event.add('c:hidden_from_recipe_viewers', itemName)

  })
}

