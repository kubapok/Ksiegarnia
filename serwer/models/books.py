from models.model import Model
import json

class Books(Model):
    collection = Model.db.books

    def isValidUserForm(book):
        try:
            book = json.loads(str(book,'utf8'))
            for o in ('ISBN','title','isEbook','lendPrice','price','availability',
            'authors','cover','tableOfContents','description'):
                if o not in book:
                    assert False
            if type(book['isEbook']) != type(True):
                assert False
            if not type(book['isEbook']) in (type(True), type(None)):
                assert False
            if type(book['tableOfContents']) != list:
                assert False
            if book['isEbook'] == True and book['lendPrice'] is None:
                assert False
            if book['isEbook'] == False and book['lendPrice'] is not None:
                assert False
            return True
        except:
                return False


    def isISBNUsed(ISBN):
        if Books.collection.find_one({'ISBN':ISBN}) is not None:
            return True
        else:
            return False


    def createBook(new_book):
        if not Books.isValidUserForm(new_book):
            return 400
        new_book = json.loads(str(new_book,'utf8'))
        if Books.isISBNUsed(new_book["ISBN"]):
            return 409
        Books.collection.insert_one(new_book)
        return 201
