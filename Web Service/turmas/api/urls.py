from django.conf.urls.defaults import patterns
from django.conf.urls.defaults import url

from piston.resource import Resource

from handlers import TurmaHandler
from handlers import AlunoHandler

turma_resource = Resource(TurmaHandler)
aluno_resource = Resource(AlunoHandler)

urlpatterns = patterns('',
	url(r'^turmas/?$', turma_resource),
	url(r'^alunos/?$', aluno_resource),

)
