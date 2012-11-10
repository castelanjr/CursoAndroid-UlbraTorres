from django.db import models

class Turma(models.Model):
	def __unicode__(self):
		return self.nome

	nome = models.CharField(max_length=100)
	professor = models.CharField(max_length=100)

	@property
	def alunos(self):
		return Aluno.objects.filter(turma = self)

class Aluno(models.Model):
	def __unicode__(self):
		return self.nome

	nome = models.CharField(max_length=100)
	idade = models.IntegerField()
	sexo = models.CharField(max_length=1)
	#turma = models.ForeignKey(Turma)
	
