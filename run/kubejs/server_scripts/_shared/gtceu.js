function bender(event, id, input, output, circ, count, time, eut) {
    event.recipes.gtceu.bender(id)
    .itemInputs(input)
    .itemOutputs(Item.of(output, count))
    .circuit(circ)
    .duration(time)
    .EUt(eut)
}

function lathe(event, id, input, output, count, time, eut) {
    event.recipes.gtceu.lathe(id)
    .itemInputs(input)
    .itemOutputs(Item.of(output, count))
    .duration(time)
    .EUt(eut)
}

function polarizer(event, id, input, output, count, time, eut) {
    event.recipes.gtceu.polarizer(id)
    .itemInputs(input)
    .itemOutputs(Item.of(output, count))
    .duration(time)
    .EUt(eut)
}