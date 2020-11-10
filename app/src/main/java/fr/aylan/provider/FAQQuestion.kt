package fr.aylan.provider


class FAQQuestion {

    var question: String = ""
    var answer: String = ""

    constructor(question: String, answer: String) {
        this.question = question
        this.answer = answer
    }

    override fun toString(): String {
        return this.question
    }

    override fun equals(other: Any?): Boolean {
        if (other is FAQQuestion) {
            return (other.question == this.question && other.answer == this.answer)
        }
        return false
    }
}

var _faq = """      
[

{
    "question" : "Questions 1",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 2",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 3",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 4",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 5",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 6",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 7",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 8",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 9",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
},
{
    "question" : "Questions 10",
    "answer" : "Pour la calculer approximativement, il suffit de multiplier le chiffre des dizaines de la vitesse par..."
}

]

""";