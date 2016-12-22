from bottle import get, post, request, route,put
from models.books import Books
import json

@get('/books')
def bookslist():
    return Books.getAll()

@get('/books/<id>')
def book(id):
    return Books.getById(id)
