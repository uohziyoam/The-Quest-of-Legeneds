# The-Quest-of-Legends

## Implementation Checklists

- [x] Board has 3 lanes and all cell types
- [x] Randomly assign heroes and monsters to last and first rows
- [x] Board is responsible for moving across Cells and informing hero/monster being moved
- [x] Heroes and Monsters maintains current position (main logic must call board ```move()```
- [x] Hero takes into account benefit of special cells when in that cell.
- [x] Heroes move separately, once each per round
- [x] Hero can transport to other lanes
- [x] Hero can move back to nexus
- [x] Hero can only enter market when in nexus
- [x] Check when encounter monster(s) and engage in fight
- [x] Automatically spawn monsters every 8(?) rounds
- [x] Check win/lose when hero or monster reach nexus of the opponent
- [ ] Transport seems to be too OP. Might need some limitation.
- [ ] ASCII art + colored text
- [ ] Address verbosity in gameplay
