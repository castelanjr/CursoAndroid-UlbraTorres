# -*- coding: utf-8 -*-
import datetime
from south.db import db
from south.v2 import SchemaMigration
from django.db import models


class Migration(SchemaMigration):

    def forwards(self, orm):
        # Deleting field 'Aluno.turma'
        db.delete_column('turmas_aluno', 'turma_id')


    def backwards(self, orm):
        # Adding field 'Aluno.turma'
        db.add_column('turmas_aluno', 'turma',
                      self.gf('django.db.models.fields.related.ForeignKey')(default=2, to=orm['turmas.Turma']),
                      keep_default=False)


    models = {
        'turmas.aluno': {
            'Meta': {'object_name': 'Aluno'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'idade': ('django.db.models.fields.IntegerField', [], {}),
            'nome': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'sexo': ('django.db.models.fields.CharField', [], {'max_length': '1'})
        },
        'turmas.turma': {
            'Meta': {'object_name': 'Turma'},
            'id': ('django.db.models.fields.AutoField', [], {'primary_key': 'True'}),
            'nome': ('django.db.models.fields.CharField', [], {'max_length': '100'}),
            'professor': ('django.db.models.fields.CharField', [], {'max_length': '100'})
        }
    }

    complete_apps = ['turmas']