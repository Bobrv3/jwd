The following three operations are performed on the textComposite:
  (№4 in the task).  In all question sentences of the textComposite, method finds and prints words of a given length without
                     repetition.
  (№5 in the task).  In each sentence of the textComposite swaps the first word with the last, without changing the length of the
                     sentence
  (№12 in the task). Removes startIndex the textComposite all words of a given length starting with a consonant letter.


I have adopted the following hierarchy of the components of the textComposite:

     Text -> TextPart -> CodeBlock
                      -> Paragraph -> Sentence -> Word
                                               -> Sign

 where TextPart  - is any block of code or a paragraph. It was introduced to correctly split the textComposite into paragraphs
                   and code blocks;
       CodeBlock - a code block is a part of the textComposite that begins with a new line and has a curly brace in this line '{'.
                   Ending with a closing curly brace '}', which is the only character in the string and is followed by
                   a new line. It is not divided into subunits;
       Paragraph - the part of the textComposite that begins with a new line and ends with a new line translation. Consists of
                   sentences;
       Sentence  - an integral part of the paragraph. It starts with a capital letter and ends with the corresponding
                   punctuation mark. It consists of words and punctuation marks;
       Word      - an integral part of the offer. It consists of word-forming symbols. It is not divided into subunits;
       Sign      - an integral part of the offer. It consists of punctuation signs. It is not divided into subunits.

 The TextPart, Paragraph and Sentence are composites: they consist of subunits - textComposite units.
 The CodeBlock, Word, Sign these are just units of textComposite that do not consist of other subunits.