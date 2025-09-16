class Card {
    id: number;
    value: number;
    suite: string;
    filename: string;

    constructor(id: number, value: number, suite: string, filename: string) {
        this.id = id;
        this.value = value;
        this.suite = suite;
        this.filename = filename;
    }
}

export default Card;