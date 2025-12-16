#!/usr/bin/env bash

# ==============================================================================
# Programmiermethoden und -werkzeuge 1
# Woche 8 - Black Jack Simulation
# Author: Rustam Khavaiashkhov
# Description: A simple Black Jack game using associative arrays and functions.
# ==============================================================================

# 1. Deck erstellen (Associative Array)
declare -A deck
declare -a player_hand
declare -a dealer_hand
player_score=0
dealer_score=0

# Populate the deck
# Suits: Hearts (H), Diamonds (D), Clubs (C), Spades (S)
suits=("H" "D" "C" "S")
# Names and Values
types=("2" "3" "4" "5" "6" "7" "8" "9" "10" "Bube" "Dame" "Koenig" "Ass")
values=(2 3 4 5 6 7 8 9 10 10 10 10 11)

# Nested loop to fill the deck (4 * 13 cards)
for suit in "${suits[@]}"; do
    for i in "${!types[@]}"; do
        card_name="${types[$i]}_of_$suit"
        card_value="${values[$i]}"
        deck["$card_name"]=$card_value
    done
done

# 2. Kartenziehen implementieren (Function)
# Arguments: $1 = target (player or dealer)
draw_card() {
    local target=$1
    
    # Get all available card keys
    local available_cards=("${!deck[@]}")
    local num_cards=${#available_cards[@]}

    # Safety check if deck is empty
    if [[ "$num_cards" -eq 0 ]]; then
        echo "Das Deck ist leer!"
        exit 1
    fi

    # Pick random index
    local rand_index=$((RANDOM % num_cards))
    local drawn_card="${available_cards[$rand_index]}"
    local card_value="${deck[$drawn_card]}"

    # Remove card from deck (realism)
    unset deck["$drawn_card"]

    # Assign to respective hand and update score
    if [[ "$target" == "player" ]]; then
        player_hand+=("$drawn_card ($card_value)")
        player_score=$((player_score + card_value))
    else
        dealer_hand+=("$drawn_card ($card_value)")
        dealer_score=$((dealer_score + card_value))
    fi
}

# Initial Deal (2 cards each)
draw_card "player"
draw_card "dealer"
draw_card "player"
draw_card "dealer"

# 3. Spielablauf für den Spieler
echo "--- Spielerzug ---"
while true; do
    echo "Deine Hand: ${player_hand[*]}"
    echo "Deine Punkte: $player_score"

    # Check for instant bust or 21
    if [[ "$player_score" -ge 21 ]]; then
        break
    fi

    read -r -p "Karte ziehen? (j/n): " choice
    if [[ "$choice" == "j" ]]; then
        draw_card "player"
    else
        break
    fi
done

# 4. Spielablauf für den Dealer
# Dealer draws only if player didn't bust
if [[ "$player_score" -le 21 ]]; then
    echo -e "\n--- Dealerzug ---"
    echo "Dealer Hand: ${dealer_hand[*]}"
    
    while [[ "$dealer_score" -lt 17 ]]; do
        echo "Dealer zieht eine Karte..."
        sleep 1
        draw_card "dealer"
        echo "Neue Hand: ${dealer_hand[*]} (Punkte: $dealer_score)"
    done
fi

# 5. Ausgabe und Fallunterscheidung (case)
echo -e "\n================ RESULTAT ================"
printf "Spieler: %d Punkte | Karten: %s\n" "$player_score" "${player_hand[*]}"
printf "Dealer:  %d Punkte | Karten: %s\n" "$dealer_score" "${dealer_hand[*]}"
echo "------------------------------------------"

# Determine game state for case statement
game_result=""

if [[ "$player_score" -gt 21 ]]; then
    game_result="PLAYER_BUST"
elif [[ "$player_score" -eq 21 ]]; then
    game_result="PLAYER_21"
elif [[ "$dealer_score" -gt 21 ]]; then
    game_result="DEALER_BUST"
elif [[ "$player_score" -gt "$dealer_score" ]]; then
    game_result="PLAYER_HIGHER"
elif [[ "$dealer_score" -gt "$player_score" ]]; then
    game_result="DEALER_HIGHER"
else
    game_result="TIE"
fi

# The required case statement
case "$game_result" in
    "PLAYER_21" | "DEALER_BUST" | "PLAYER_HIGHER")
        echo "GEWONNEN! Der Spieler gewinnt."
        ;;
    "PLAYER_BUST" | "DEALER_HIGHER")
        echo "VERLOREN! Der Dealer gewinnt."
        ;;
    "TIE")
        echo "UNENTSCHIEDEN!"
        ;;
    *)
        echo "Fehler im Spielablauf."
        ;;
esac
echo "=========================================="