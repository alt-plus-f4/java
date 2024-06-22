package valio;

class Instrument extends Item implements IUsableItem {
    public Instrument(String name) {
        super(name);
    }

    @Override
    public void use(Farm farm, Vector2 position) {
        use(farm, position, Vector2.zero, 1);
    }

    @Override
    public void use(Farm farm, Vector2 position, Vector2 direction, int powerLevel) {
        int startX = position.getX();
        int startY = position.getY();

        for (int i = 0; i < powerLevel; i++) {
            int x = startX + i * direction.getX();
            int y = startY + i * direction.getY();

            if (x >= 0 && x < 10 && y >= 0 && y < 10) {
                Cell cell = farm.getCell(new Vector2(x, y));
                cell.applyTool(this);
            }
        }
    }
}

