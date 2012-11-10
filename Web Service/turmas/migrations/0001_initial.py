# -*- coding: utf-8 -*-
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models


class Migration(SchemaMigration):

    def forwards(self, orm):
        # Adding model 'Turma'
        db.create_table('turmas_turma', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('nome', self.gf('django.db.models.fields.CharField')(max_length=100)),
            ('professor', self.gf('django.db.models.fields.CharField')(max_length=100)),
        ))
        db.send_create_signal('turmas', ['Turma'])

        # Adding model 'Aluno'
        db.create_table('turmas_aluno', (
            ('id', self.gf('django.db.models.fields.AutoField')(primary_key=True)),
            ('nome', self.gf('django.db.models.fields.CharField')(max_length=100)),
            ('idade', self.gf('django.db.models.fields.IntegerField')()),
            ('sexo', self.gf('django.db.models.fields.CharField')(max_length=1)),
            ('turma', self.gf('django.db.models.fields.related.ForeignKey')(to=orm['turmas.Turma'])),
        ))
        db.send_create_signal('turmas', ['Aluno'])


    def backwards(self, orm):
        # Deleting model 'Turma'
        db.delete_table('turmas_turma')

        # Deleting model 'Aluno'
        db.delete_table('turmas_aluno')


    models = {
        'turmas.aluno': {
            'Meta': {'object_name': 'Aluno'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'idade': ('django.db.models.fields.IntegerField', [], {}),
            'nome': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'sexo': ('django.db.models.fields.CharField', [], {'max_length': '1'}),
            'turma': ('django.db.models.fields.related.ForeignKey', [], {'to': "orm['turmas.Turma']"})
        },
        'turmas.turma': {
            'Meta': {'object_name': 'Turma'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'nome': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'professor': ('django.db.models.fields.CharField', [], {'max_length': '100'})
        }
    }

    complete_apps = ['turmas']