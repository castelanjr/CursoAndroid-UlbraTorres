from piston.handler import BaseHandler
from piston.utils import rc

from turmas.models import Turma
from turmas.models import Aluno

class Handler(BaseHandler):
	allowed_methods = ('GET', 'POST',)
	
	def create(self, request, obj = None):
		data = self.flatten_dict(request.data)
		inst = self.model(**data)
		inst.save()
		return rc.CREATED

	def read(self, request, obj = None):
		if obj == None:
			return self.model.objects.all()
		else:
			return self.model.objects.get(id = obj)

class TurmaHandler(Handler):
	model = Turma
	fields = ('id', 'nome', 'professor', 'alunos')

class AlunoHandler(Handler):
	model = Aluno
	fields = ('id', 'nome', 'idade', 'sexo')
